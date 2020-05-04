package com.sabgil.contena.response.subscription

import com.fasterxml.jackson.annotation.JsonProperty
import com.sabgil.contena.entitiy.ShopEntity

data class PostUnsubscriptionResponse(
        @JsonProperty("user_id") val userId: String,
        @JsonProperty("shop_name") val shopName: String,
        @JsonProperty("subscriber_count") val subscriberCount: Long
) {
    companion object {
        fun from(userId: String, shopEntity: ShopEntity): PostUnsubscriptionResponse =
                PostUnsubscriptionResponse(
                        userId = userId,
                        shopName = shopEntity.shopName,
                        subscriberCount = shopEntity.subscriptionEntities.size.toLong()
                )
    }
}