package com.sabgil.contena.response.shop

import com.fasterxml.jackson.annotation.JsonProperty
import com.sabgil.contena.entitiy.ShopEntity

data class GetSubscriptionShopListResponse(
        @JsonProperty("shop_list") val shopList: List<Shop>
) {
    data class Shop(
            @JsonProperty("shop_name") val shopName: String,
            @JsonProperty("shop_logo_url") val shopLogoUrl: String,
            @JsonProperty("subscriber_count") val subscriberCount: Long,
            @JsonProperty("shop_description") val shopDescription: String,
            @JsonProperty("is_subscribed") val isSubscribed: Boolean
    )

    companion object {
        fun from(shopEntities: List<ShopEntity>) =
                GetSubscriptionShopListResponse(
                        shopList = shopEntities.map { it.mapToShop() }
                )

        private fun ShopEntity.mapToShop() = Shop(
                shopName = shopName,
                shopLogoUrl = shopLogoUrl,
                subscriberCount = subscriptionEntities.size.toLong(),
                shopDescription = shopDescription,
                isSubscribed = true
        )
    }
}