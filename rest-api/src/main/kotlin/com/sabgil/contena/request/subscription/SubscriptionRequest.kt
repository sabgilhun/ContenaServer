package com.sabgil.contena.request.subscription

import org.springframework.web.bind.annotation.RequestParam

data class SubscriptionRequest(
        @RequestParam("user_id") var userId: String = "",
        @RequestParam("shop_name") var shopName: String = ""
)