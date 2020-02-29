package com.sabgil.contena.controller

import com.sabgil.contena.exceptiom.BadRequestException
import com.sabgil.contena.repository.ShopRepository
import com.sabgil.contena.request.notification.NotiNewItemRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.BatchResponse
import com.google.firebase.messaging.MulticastMessage
import com.google.firebase.messaging.Notification


@RestController
class NotificationController(
        private val shopRepository: ShopRepository
) {

    @PostMapping("/notification/new_item")
    fun postNotificationNewItem(
            @RequestBody notiNewItemRequest: NotiNewItemRequest
    ) {
        if (notiNewItemRequest.shopList.isEmpty()) {
            throw BadRequestException("갱신된 쇼핑몰 리스트가 비어 있습니다.")
        }

        val hasToPushMessageUserList =
                notiNewItemRequest.shopList.fold(mutableSetOf<String>()) { userTokenSet, shopName ->
                    val shopEntity = shopRepository.findByShopName(shopName)

                    if (shopEntity != null) {
                        userTokenSet.addAll(shopEntity.subscriptionEntities.map { it.userId })
                    }
                    
                    return@fold userTokenSet
                }.toList()

        val message = MulticastMessage.builder()
                .addAllTokens(hasToPushMessageUserList)
                .build()

        FirebaseMessaging.getInstance().sendMulticast(message)
    }
}