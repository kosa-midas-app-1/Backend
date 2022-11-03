package com.example.jobda.domain.company.controller.dto.response

import java.util.UUID

/**
 *
 * SearchCompanyResponse
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
data class SearchCompanyResponse(
    val companies: List<CompanyElement>
) {
    data class CompanyElement(
        val id: UUID,
        val name: String,
        val managerName: String,
        val managerPhoneNumber: String
    )
}