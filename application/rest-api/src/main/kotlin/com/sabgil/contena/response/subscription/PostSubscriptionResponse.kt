package com.sabgil.contena.response.subscription

import com.fasterxml.jackson.annotation.JsonProperty
import com.sabgil.contena.entitiy.ShopEntity

data class PostSubscriptionResponse(
        @JsonProperty("user_id") val userId: String,
        @JsonProperty("updated_shop") val updatedShop: Shop
) {
    data class Shop(
            @JsonProperty("shop_name") val shopName: String,
            @JsonProperty("shop_logo_url") val shopLogoUrl: String,
            @JsonProperty("subscriber_count") val subscriberCount: Long,
            @JsonProperty("shop_description") val shopDescription: String,
            @JsonProperty("is_subscribed") val isSubscribed: Boolean
    )

    companion object {
        fun from(userId: String, shopEntity: ShopEntity) = PostSubscriptionResponse(
                userId = userId,
                updatedShop = shopEntity.mapToShop()
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