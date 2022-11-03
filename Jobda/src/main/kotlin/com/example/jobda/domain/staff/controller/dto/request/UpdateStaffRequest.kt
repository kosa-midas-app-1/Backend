package com.example.jobda.domain.staff.controller.dto.request

/**
 *
 * UpdateStaffRequest
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
data class UpdateStaffRequest(
    val email: String,
    val phoneNumber: String,
    val position: String
)