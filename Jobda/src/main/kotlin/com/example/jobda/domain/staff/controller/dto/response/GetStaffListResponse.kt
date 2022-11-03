package com.example.jobda.domain.staff.controller.dto.response

import com.example.jobda.domain.staff.type.Status
import java.util.UUID

/**
 *
 * GetStaffWorkTime
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
data class GetStaffListResponse(
    val staff: List<StaffElement>
) {
    data class StaffElement(
        val id: UUID,
        val name: String,
        val position: String,
        val status: Status,
        val todayWorkTime: Int
    )
}
