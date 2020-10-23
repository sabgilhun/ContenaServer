package com.sabgil.contena.controller

import com.sabgil.contena.entitiy.RecommendEntity
import com.sabgil.contena.entitiy.SubscriptionEntity
import com.sabgil.contena.repository.RecommendRepository
import com.sabgil.contena.repository.ShopRepository
import com.sabgil.contena.repository.SubscriptionRepository
import com.sabgil.contena.response.shop.GetAllShopListResponse
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

    @GetMapping("/shop_list/all")
    fun getAllShopList(
            @RequestParam(value = "user_id", defaultValue = "") userId: String
    ): GetAllShopListResponse {
        val shopEntities = shopRepository.findAll()
        val subscriptionList = subscriptionRepository.findByUserId(userId)

        return GetAllShopListResponse.from(shopEntities, subscriptionList)
    }

    @GetMapping("/shop_list/recommend")
    fun getRecommendShopList(
            @RequestParam(value = "user_id", defaultValue = "") userId: String
    ): GetRecommendShopListResponse {
        val shopEntities = recommendRepository.findAll()
                .mapNotNull(RecommendEntity::shopEntity)
        val subscriptionList = subscriptionRepository.findByUserId(userId)

        return GetRecommendShopListResponse.from(shopEntities, subscriptionList)
    }

    @GetMapping("/shop_list/available")
    fun getAvailableShopList(
            @RequestParam(value = "user_id", defaultValue = "") userId: String,
            @RequestParam(value = "search_keyword", defaultValue = "") searchKeyword: String
    ): GetAvailableShopListResponse {
        val shopEntities = if (searchKeyword.isNotEmpty()) {
            shopRepository.findByShopNameContaining(searchKeyword)
        } else {
            emptyList()
        }
        val subscriptionList = subscriptionRepository.findByUserId(userId)

        return GetAvailableShopListResponse.from(shopEntities, subscriptionList)
    }

    @GetMapping("/shop_list/subscription")
    fun getSubscriptionShopList(
            @RequestParam(value = "user_id", defaultValue = "") userId: String
    ): GetSubscriptionShopListResponse {
        val shopEntities = subscriptionRepository.findByUserId(userId)
                .mapNotNull(SubscriptionEntity::shopEntity)

        return GetSubscriptionShopListResponse.from(shopEntities)
    }
}
