package com.sabgil.contena.response.shop

import com.fasterxml.jackson.annotation.JsonProperty
import com.sabgil.contena.entitiy.ShopEntity

data class GetRecommendShopListResponse(
        @JsonProperty("shop_list") val shopList: List<Shop>
) {
    data class Shop(
            @JsonProperty("shop_name") val shopName: String,
            @JsonProperty("shop_logo_url") val shopLogoUrl: String,
            @JsonProperty("subscriber_count") val subscriberCount: Long,
            @JsonProperty("shop_description") val shopDescription: String
    )

    companion object {
        fun from(shopEntities: List<ShopEntity>) = GetRecommendShopListResponse(
                shopList = shopEntities.map { it.mapToShop() }
        )

        private fun ShopEntity.mapToShop() = Shop(
                shopName = shopName,
                shopLogoUrl = shopLogoUrl,
                subscriberCount = subscriptionEntities.size.toLong(),
                shopDescription = shopDescription
        )
    }
}