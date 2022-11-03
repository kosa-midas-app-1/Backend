package com.example.jobda.domain.company.controller.dto

import com.example.jobda.domain.company.controller.dto.request.SetRequiredWorkTimeRequest
import com.example.jobda.domain.company.controller.dto.request.UpdateCompanyInfoRequest
import com.example.jobda.domain.company.controller.dto.response.GetCompanyInfoResponse
import com.example.jobda.domain.company.controller.dto.response.GetCompanyRuleResponse
import com.example.jobda.domain.company.controller.dto.response.SearchCompanyResponse
import com.example.jobda.domain.company.service.GetCompanyInfoService
import com.example.jobda.domain.company.service.GetCompanyRuleService
import com.example.jobda.domain.company.service.SearchCompanyService
import com.example.jobda.domain.company.service.SetRequiredWorkTimeService
import com.example.jobda.domain.company.service.UpdateCompanyInfoService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 *
 * CompanyRestController
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@RequestMapping("/companies")
@RestController
class CompanyRestController(
    private val setRequiredWorkTimeService: SetRequiredWorkTimeService,
    private val updateCompanyInfoService: UpdateCompanyInfoService,
    private val searchCompanyService: SearchCompanyService,
    private val getCompanyInfoService: GetCompanyInfoService,
    private val getCompanyRuleService: GetCompanyRuleService
) {

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/work-time")
    fun setRequiredWorkTime(@RequestBody @Valid request: SetRequiredWorkTimeRequest) {
        setRequiredWorkTimeService.execute(request)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    fun updateCompanyInfo(@RequestBody @Valid request: UpdateCompanyInfoRequest) {
        updateCompanyInfoService.execute(request)
    }

    @GetMapping("/search")
    fun searchCompany(@RequestParam(required = true) name: String): SearchCompanyResponse {
        return searchCompanyService.execute(name)
    }

    @GetMapping
    fun getCompanyInfo(): GetCompanyInfoResponse {
        return getCompanyInfoService.execute()
    }

    @GetMapping
    fun getCompanyRule(): GetCompanyRuleResponse {
        return getCompanyRuleService.execute()
    }
}