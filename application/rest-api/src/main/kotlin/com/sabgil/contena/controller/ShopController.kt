package com.sabgil.contena.controller

import com.sabgil.contena.entitiy.RecommendEntity
import com.sabgil.contena.entitiy.ShopEntity
import com.sabgil.contena.repository.RecommendRepository
import com.sabgil.contena.repository.ShopRepository
import com.sabgil.contena.repository.SubscriptionRepository
import com.sabgil.contena.response.shop.GetAvailableShopListResponse
import com.sabgil.contena.response.shop.GetRecommendShopListResponse
import com.sabgil.contena.response.shop.GetSubscriptionShopListResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ShopController(
        private val shopRepository: ShopRepository,
        private val recommendRepository: RecommendRepository,
        private val subscriptionRepository: SubscriptionRepository
) {

    @GetMapping("/shop_list/recommend")
    fun getRecommendShopList(): GetRecommendShopListResponse {

        val shopEntities = mutableListOf<ShopEntity>()

        recommendRepository.findAll().mapNotNullTo(shopEntities, RecommendEntity::shopEntity)

        return GetRecommendShopListResponse.from(shopEntities)
    }

    @GetMapping("/shop_list/available")
    fun getAvailableShopList(
            @RequestParam(value = "search_keyword", defaultValue = "") searchKeyword: String
    ): GetAvailableShopListResponse {

        val shopEntities = if (searchKeyword.isNotEmpty()) {
            shopRepository.findByShopNameContaining(searchKeyword)
        } else {
            emptyList()
        }

        return GetAvailableShopListResponse.from(shopEntities)
    }

    @GetMapping("/shop_list/subscription")
    fun getSubscriptionShopList(
            @RequestParam(value = "user_id", defaultValue = "") userId: String
    ): GetSubscriptionShopListResponse {
        val shopEntities = subscriptionRepository.findByUserId(userId)
                .filter { it.shopEntity != null }
                .map { it.shopEntity!! }

        return GetSubscriptionShopListResponse.from(shopEntities)
    }
}
