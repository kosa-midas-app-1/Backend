package com.example.jobda.domain.staff.controller.dto.response

/**
 *
 * GetMyInfoResponse
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
data class GetMyInfoResponse(
    val name: String,
    val phoneNumber: String,
    val email: String,
    val companyName: String?,
    val position: String?
)
