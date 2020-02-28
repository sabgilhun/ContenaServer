package com.sabgil.contena.controller

import com.sabgil.contena.entitiy.SubscriptionEntity
import com.sabgil.contena.exceptiom.NotFoundException
import com.sabgil.contena.repository.ShopRepository
import com.sabgil.contena.repository.SubscriptionRepository
import com.sabgil.contena.request.subscription.SubscriptionRequest
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import java.lang.Exception

@RestController
class SubscriptionController(
        private val subscriptionRepository: SubscriptionRepository,
        private val shopRepository: ShopRepository
) {

    @PostMapping("/subscription")
    fun postShopSubscription(
            @RequestBody subscriptionRequest: SubscriptionRequest
    ) {
        checkSubscriptionAndPost(subscriptionRequest.userId, subscriptionRequest.shopName)
    }

    @DeleteMapping("/subscription")
    fun deleteShopSubscription(
            @RequestBody subscriptionRequest: SubscriptionRequest
    ) {
        checkSubscriptionAndDelete(subscriptionRequest.userId, subscriptionRequest.shopName)
    }

    @Transactional
    fun checkSubscriptionAndPost(userId: String, shopName: String) {
        val shopEntity = shopRepository.findById(shopName)

        if (shopEntity.isEmpty) {
            throw NotFoundException("해당 쇼핑몰은 쇼핑몰 리스트에 존재하지 않습니다.")
        }

        val subscriptionEntity = subscriptionRepository.findByUserIdAndShopEntity(userId, shopEntity.get())

        if (subscriptionEntity.isEmpty()) {
            subscriptionRepository.save(SubscriptionEntity(userId = userId, shopEntity = shopEntity.get()))
            shopRepository.updateIncreaseSubscriberCount(shopName)
        } else {
            throw NotFoundException("이미 구독한 쇼핑몰 입니다.")
        }
    }

    @Transactional
    fun checkSubscriptionAndDelete(userId: String, shopName: String) {
        val shopEntity = shopRepository.findById(shopName)

        if (shopEntity.isEmpty) {
            throw NotFoundException("해당 쇼핑몰은 쇼핑몰 리스트에 존재하지 않습니다.")
        }

        val subscriptionEntity = subscriptionRepository.findByUserIdAndShopEntity(userId, shopEntity.get())

        if (subscriptionEntity.isNotEmpty()) {
            subscriptionRepository.delete(subscriptionEntity.first())
            shopRepository.updateDecreaseSubscriberCount(shopName)
        } else {
            throw NotFoundException("이미 구독 취소한 쇼핑몰 입니다.")
        }
    }
}
