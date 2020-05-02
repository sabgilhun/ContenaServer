package com.sabgil.contena.response.subscription

import com.fasterxml.jackson.annotation.JsonProperty
import com.sabgil.contena.entitiy.ShopEntity

data class PostSubscriptionResponse(
        @JsonProperty("user_id") val userId: String,
        @JsonProperty("shop_name") val shopName: String
) {
    companion object {
        fun from(userId: String, shopEntity: ShopEntity): PostSubscriptionResponse =
                PostSubscriptionResponse(
                        userId = userId,
                        shopName = shopEntity.shopName
                )
    }
}