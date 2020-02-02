package com.sabgil.contena.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SubscriptionController {

    @PostMapping("/subscription")
    fun postShopSubscription(
            @RequestParam(value = "user_id", defaultValue = "") userId: String,
            @RequestParam(value = "shop_name", defaultValue = "") shopName: String
    ) {
        TODO()
    }

    @DeleteMapping("/subscription")
    fun deleteShopSubscription(
            @RequestParam(value = "user_id", defaultValue = "") userId: String,
            @RequestParam(value = "shop_name", defaultValue = "") shopName: String
    ) {
        TODO()
    }
}
