package com.example.jobda.domain.company.service

import com.example.jobda.domain.company.controller.dto.response.SearchCompanyResponse
import com.example.jobda.domain.company.repository.CompanyRepository
import org.springframework.stereotype.Service

/**
 *
 * SearchCompanyService
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Service
class SearchCompanyService(
    private val companyRepository: CompanyRepository
) {

    fun execute(name: String): SearchCompanyResponse {

        val companies = companyRepository.findByNameContaining(name).map {
            val manager = it.managerEntity!!

            SearchCompanyResponse.CompanyElement(
                id = it.id,
                name = it.name,
                managerName = manager.name,
                managerPhoneNumber = manager.phoneNumber
            )
        }

        return SearchCompanyResponse(companies)
    }
}