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
        val shopEntity = shopRepository.findByShopName(subscriptionRequest.shopName)
                ?: throw NotFoundException("해당 쇼핑몰은 쇼핑몰 리스트에 존재하지 않습니다.")

        val subscriptionEntity =
                subscriptionRepository.findByUserIdAndShopEntity(
                        userId = subscriptionRequest.userId,
                        shopEntity = shopEntity
                )

        if (subscriptionEntity == null) {
            subscriptionRepository.save(SubscriptionEntity(
                    userId = subscriptionRequest.userId,
                    shopEntity = shopEntity
            ))
        } else {
            throw NotFoundException("이미 구독한 쇼핑몰 입니다.")
        }
    }

    @PostMapping("/unsubscription")
    fun postShopUnsubscription(
            @RequestBody subscriptionRequest: SubscriptionRequest
    ) {
        val shopEntity = shopRepository.findByShopName(subscriptionRequest.shopName)
                ?: throw NotFoundException("해당 쇼핑몰은 쇼핑몰 리스트에 존재하지 않습니다.")

        val subscriptionEntity =
                subscriptionRepository.findByUserIdAndShopEntity(
                        userId = subscriptionRequest.userId,
                        shopEntity = shopEntity
                )

        if (subscriptionEntity != null) {
            subscriptionRepository.delete(subscriptionEntity)
        } else {
            throw NotFoundException("이미 구독 취소한 쇼핑몰 입니다.")
        }
    }
}
