package com.example.jobda.domain.company.controller.dto.response

import com.example.jobda.domain.company.type.WorkSystem
import java.time.LocalDateTime

/**
 *
 * GetCompanyInfoResponse
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
data class GetCompanyInfoResponse(
    val profileImageUrl: String,
    val name: String,
    val businessNumber: String,
    val staffCount: Int,
    val applyWorkHome: Boolean,
    val workSystem: WorkSystem,
    val requiredWorkTime: RequiredWorkTime?
) {
    data class RequiredWorkTime(
        val startAt: LocalDateTime,
        val endAt: LocalDateTime
    )
}