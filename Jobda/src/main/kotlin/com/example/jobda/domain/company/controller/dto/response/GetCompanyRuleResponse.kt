package com.example.jobda.domain.company.controller.dto.response

import com.example.jobda.domain.company.type.WorkSystem
import java.time.LocalDateTime

/**
 *
 * GetCompanyRuleResponse
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
data class GetCompanyRuleResponse(
    val applyWorkHome: Boolean,
    val workSystem: WorkSystem,
    val requiredWorkTime: RequiredWorkTime?
) {
    data class RequiredWorkTime(
        val startAt: LocalDateTime,
        val endAt: LocalDateTime
    )
}