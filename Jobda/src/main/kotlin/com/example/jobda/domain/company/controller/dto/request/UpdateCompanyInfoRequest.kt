package com.example.jobda.domain.company.controller.dto.request

import com.example.jobda.domain.company.type.WorkSystem

/**
 *
 * UpdateCompanyInfoRequest
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
data class UpdateCompanyInfoRequest(
    val name: String,
    val profileImageUrl: String,
    val workSystem: WorkSystem,
    val applyWorkHome: Boolean
)