package com.example.jobda.domain.staff.controller.dto.response


/**
 *
 * GetTimeLineResponse
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
data class GetTimeLineResponse(
    val hours: List<HourElement>
) {
    data class HourElement(
        val hour: Int,
        val workingStaffCount: Int
    )
}