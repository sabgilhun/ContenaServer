package com.sabgil.contena.response.shop

import com.fasterxml.jackson.annotation.JsonProperty
import com.sabgil.contena.entitiy.ShopEntity
import com.sabgil.contena.entitiy.SubscriptionEntity

data class GetRecommendShopListResponse(
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
        fun from(shopEntities: List<ShopEntity>, userSubscriptionEntities: List<SubscriptionEntity>) =
                GetRecommendShopListResponse(
                        shopList = shopEntities.map { it.mapToShop(userSubscriptionEntities) }
                )

        private fun ShopEntity.mapToShop(userSubscriptionEntities: List<SubscriptionEntity>) = Shop(
                shopName = shopName,
                shopLogoUrl = shopLogoUrl,
                subscriberCount = subscriptionEntities.size.toLong(),
                shopDescription = shopDescription,
                isSubscribed = userSubscriptionEntities.map(SubscriptionEntity::shopEntity).contains(this)
        )
    }
}