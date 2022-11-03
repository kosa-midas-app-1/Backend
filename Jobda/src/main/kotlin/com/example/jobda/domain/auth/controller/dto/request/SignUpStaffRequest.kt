package com.example.jobda.domain.auth.controller.dto.request

import java.util.UUID

/**
 *
 * SignUpStaffRequest
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
data class SignUpStaffRequest(
    val email: String,
    val password: String,
    val phoneNumber: String,
    val name: String,
    val companyId: UUID,
    val position: String
)