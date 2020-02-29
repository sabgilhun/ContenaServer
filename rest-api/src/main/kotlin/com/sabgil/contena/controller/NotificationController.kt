package com.sabgil.contena.controller

import com.sabgil.contena.exceptiom.BadRequestException
import com.sabgil.contena.repository.ShopRepository
import com.sabgil.contena.request.notification.NotiNewItemRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

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
                notiNewItemRequest.shopList.fold(mutableListOf<String>()) { userTokenList, shopName ->
                    val shopEntity = shopRepository.findByShopName(shopName)

                    if (shopEntity != null) {
                        userTokenList.addAll(shopEntity.subscriptionEntities.map { it.userId })
                    }

                    return@fold userTokenList
                }
    }
}