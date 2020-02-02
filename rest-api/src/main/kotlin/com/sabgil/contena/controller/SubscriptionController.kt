package com.sabgil.contena.controller

import com.sabgil.contena.entitiy.SubscriptionEntity
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
            throw Exception()
        }

        val subscriptionEntity = subscriptionRepository.findByUserIdAndShopEntity(userId, shopEntity.get())

        if (subscriptionEntity.isEmpty()) {
            subscriptionRepository.save(SubscriptionEntity(userId = userId, shopEntity = shopEntity.get()))
            shopRepository.updateIncreaseSubscriberCount(shopName)
        } else {
            throw Exception()
        }
    }

    @Transactional
    fun checkSubscriptionAndDelete(userId: String, shopName: String) {
        val shopEntity = shopRepository.findById(shopName)

        if (shopEntity.isEmpty) {
            throw Exception()
        }

        val subscriptionEntity = subscriptionRepository.findByUserIdAndShopEntity(userId, shopEntity.get())

        if (subscriptionEntity.isNotEmpty()) {
            subscriptionRepository.delete(subscriptionEntity.first())
            shopRepository.updateDecreaseSubscriberCount(shopName)
        } else {
            throw Exception()
        }
    }
}
