package com.sabgil.contena.request.notification

import com.fasterxml.jackson.annotation.JsonProperty

data class NotiNewItemRequest(
        @JsonProperty("notification_title") var notiTitle: String? = "",
        @JsonProperty("notification_body") var notiBody: String? = "",
        @JsonProperty("shop_list") var shopList: List<String> = emptyList()
)