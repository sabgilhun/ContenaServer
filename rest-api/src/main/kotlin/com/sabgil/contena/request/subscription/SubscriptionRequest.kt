package com.sabgil.contena.request.subscription

import com.fasterxml.jackson.annotation.JsonProperty

data class SubscriptionRequest(
        @JsonProperty("user_id") var userId: String = "",
        @JsonProperty("shop_name") var shopName: String = ""
)