package com.sabgil.contena.response.shop

import com.sabgil.contena.entitiy.ShopEntity

data class GetShopListResponse(
        val availableShopList: List<AvailableShop>
) {
    data class AvailableShop(
            val shopName: String,
            val shopLogUrl: String,
            val subscriberCount: Long
    )

    companion object {
        fun from(shopEntities: List<ShopEntity>) = GetShopListResponse(
                availableShopList = shopEntities.map { it.mapToAvailableShop() }
        )

        private fun ShopEntity.mapToAvailableShop() = AvailableShop(
                shopName = shopName,
                shopLogUrl = shopLogoUrl,
                subscriberCount = subscriberCount
        )
    }
}