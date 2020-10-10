package com.sabgil.contena.controller

import com.sabgil.contena.entitiy.ReportEntity
import com.sabgil.contena.repository.ReportRepository
import com.sabgil.contena.request.report.ReportRequest
import com.sabgil.contena.response.report.ReportResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ReportController(
        private val reportRepository: ReportRepository
) {
    @PostMapping("/report")
    fun postReport(
            @RequestBody reportRequest: ReportRequest
    ): ReportResponse {
        val reportKey = ReportEntity.ReportKey(user_id = reportRequest.userId, post_id = reportRequest.post_id)

        val preexistenceReport = reportRepository.findById(reportKey)

        val isNotReported = preexistenceReport.isEmpty
        if (isNotReported) {
            reportRepository.save(
                    ReportEntity(
                            reportKey = reportKey,
                            contents = applyContentsLengthRestrictions(reportRequest.contents)
                    )
            )
        }

        return ReportResponse.from(reportRequest, isNotReported)
    }

    private fun applyContentsLengthRestrictions(contents: String) =
            if (contents.length > 150) {
                contents.substring(0 until 150)
            } else {
                contents
            }
}