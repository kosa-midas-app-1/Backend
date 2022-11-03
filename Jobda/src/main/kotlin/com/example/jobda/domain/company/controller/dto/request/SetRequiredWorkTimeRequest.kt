package com.example.jobda.domain.company.controller.dto.request

import java.time.LocalDateTime

/**
 *
 * SetRequiredWorkTimeRequest
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
data class SetRequiredWorkTimeRequest(
    val startAt: LocalDateTime,
    val endAt: LocalDateTime
)
