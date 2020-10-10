package com.sabgil.contena.response.report

import com.fasterxml.jackson.annotation.JsonProperty
import com.sabgil.contena.request.report.ReportRequest

data class ReportResponse(
        @JsonProperty("user_id") var userId: String = "",
        @JsonProperty("post_id") var post_id: Long = 0L,
        @JsonProperty("contents") var contents: String = "",
        @JsonProperty("reported") var reported: Boolean = false
) {
    companion object {
        fun from(reportRequest: ReportRequest, reported: Boolean): ReportResponse =
                ReportResponse(
                        userId = reportRequest.userId,
                        post_id = reportRequest.post_id,
                        contents = reportRequest.contents,
                        reported = reported
                )
    }
}