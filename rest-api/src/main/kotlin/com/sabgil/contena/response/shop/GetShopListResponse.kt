package com.sabgil.contena.response.shop

import com.fasterxml.jackson.annotation.JsonProperty
import com.sabgil.contena.entitiy.ShopEntity

data class GetShopListResponse(
        @JsonProperty("available_shop_list") val availableShopList: List<AvailableShop>
) {
    data class AvailableShop(
            @JsonProperty("shop_name") val shopName: String,
            @JsonProperty("shop_logo_url") val shopLogoUrl: String,
            @JsonProperty("subscriber_count") val subscriberCount: Long
    )

    companion object {
        fun from(shopEntities: List<ShopEntity>) = GetShopListResponse(
                availableShopList = shopEntities.map { it.mapToAvailableShop() }
        )

        private fun ShopEntity.mapToAvailableShop() = AvailableShop(
                shopName = shopName,
                shopLogoUrl = shopLogoUrl,
                subscriberCount = subscriberCount
        )
    }
}