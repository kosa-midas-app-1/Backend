package com.example.jobda.domain.auth.controller.dto.request

import com.example.jobda.domain.company.type.WorkSystem

/**
 *
 * SignUpManagerRequest
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
data class SignUpManagerRequest(
    val email: String,
    val password: String,
    val name: String,
    val phoneNumber: String,
    val companyName: String,
    val companyBusinessNumber: String,
    val companyProfileImageUrl: String,
    val companyWorkSystem: WorkSystem,
    val companyApplyWorkHome: Boolean
)
