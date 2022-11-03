package com.example.jobda.domain.company.error

import com.example.jobda.infrastructure.error.custom.CustomErrorProperty

/**
 *
 * CompanyErrorCode
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
enum class CompanyErrorCode(
    private val status: Int,
    private val message: String
) : CustomErrorProperty {

    COMPANY_NOT_FOUND_EXCEPTION(404, "Company Not Found")
    ;

    override fun status(): Int = status
    override fun message(): String = message
}