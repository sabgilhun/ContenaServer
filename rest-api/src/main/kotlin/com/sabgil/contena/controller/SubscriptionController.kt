package com.sabgil.contena.controller

import com.sabgil.contena.request.subscription.SubscriptionRequest
import org.springframework.web.bind.annotation.*

@RestController
class SubscriptionController {

    @PostMapping("/subscription")
    fun postShopSubscription(
            @RequestBody subscriptionRequest: SubscriptionRequest
    ) {
        TODO()
    }

    @DeleteMapping("/subscription")
    fun deleteShopSubscription(
            @RequestBody subscriptionRequest: SubscriptionRequest
    ) {
        TODO()
    }
}
