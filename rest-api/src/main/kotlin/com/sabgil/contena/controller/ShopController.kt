package com.sabgil.contena.controller

import com.sabgil.contena.entitiy.ShopEntity
import com.sabgil.contena.repository.ShopRepository
import com.sabgil.contena.repository.SubscriptionRepository
import com.sabgil.contena.response.shop.GetShopListResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ShopController(
        private val shopRepository: ShopRepository,
        private val subscriptionRepository: SubscriptionRepository
) {

    @GetMapping("/shop_list/available")
    fun getAvailableShopList(
            @RequestParam(value = "search_keyword", defaultValue = "") searchKeyword: String
    ): GetShopListResponse {

        val shopEntities = if (searchKeyword.isNotEmpty()) {
            shopRepository.findByShopNameContaining(searchKeyword)
        } else {
            emptyList()
        }

        return GetShopListResponse.from(shopEntities)
    }

    @GetMapping("/shop_list/subscription")
    fun getSubscriptionShopList(
            @RequestParam(value = "user_id", defaultValue = "") userId: String
    ): GetShopListResponse {
        val shopEntities = subscriptionRepository.findByUserId(userId)
                .filter { it.shopEntity != null }
                .map { it.shopEntity!! }

        return GetShopListResponse.from(shopEntities)
    }
}
