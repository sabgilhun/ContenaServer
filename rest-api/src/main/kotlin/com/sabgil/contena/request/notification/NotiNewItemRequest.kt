package com.sabgil.contena.request.notification

import com.fasterxml.jackson.annotation.JsonProperty

data class NotiNewItemRequest(
        @JsonProperty("shop_list") var shopList: List<String>? = null
)