package com.sabgil.contena.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ShopController {

    @GetMapping("/shop_list")
    fun getAvailableShopList(
            @RequestParam(value = "search_keyword", defaultValue = "") searchKeyword: String
    ) {
        TODO()
    }

    @GetMapping("/shop_list")
    fun getSubscriptionShopList(
            @RequestParam(value = "user_id", defaultValue = "") userId: String
    ) {
        TODO()
    }
}
