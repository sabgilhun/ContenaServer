package com.sabgil.contena.request.report

import com.fasterxml.jackson.annotation.JsonProperty

data class ReportRequest(
        @JsonProperty("user_id") var userId: String = "",
        @JsonProperty("post_id") var post_id: Long = 0L,
        @JsonProperty("contents") var contents: String = ""
)