package com.example.jobda.domain.staff.controller.dto.response

import java.time.LocalDateTime
import java.util.UUID

/**
 *
 * GetStaffDetailsResponse
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
data class GetStaffDetailsResponse(
    val id: UUID,
    val name: String,
    val position: String,
    val email: String,
    val phoneNumber: String,
    val startAt: LocalDateTime?,
    val endAt: LocalDateTime?,
    val totalWork: Int,
    val todayWork: Int,
    val averageWork: Int,
    val averageStart: LocalDateTime
)
