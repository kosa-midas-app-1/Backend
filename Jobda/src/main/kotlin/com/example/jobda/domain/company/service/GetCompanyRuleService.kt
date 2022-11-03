package com.example.jobda.domain.company.service

import com.example.jobda.domain.company.controller.dto.response.GetCompanyRuleResponse
import com.example.jobda.domain.company.repository.RequiredWorkTimeRepository
import com.example.jobda.domain.company.type.WorkSystem
import com.example.jobda.domain.manager.exception.StaffNotFoundException
import com.example.jobda.domain.staff.repository.StaffRepository
import com.example.jobda.infrastructure.security.util.SecurityUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

/**
 *
 * GetCompanyRuleResponse
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Service
class GetCompanyRuleService(
    private val securityUtil: SecurityUtil,
    private val staffRepository: StaffRepository,
    private val requiredWorkTimeRepository: RequiredWorkTimeRepository
) {

    fun execute(): GetCompanyRuleResponse {

        val staffId = securityUtil.getCurrentUserId()
        val staffEntity = staffRepository.findByIdOrNull(staffId) ?: throw StaffNotFoundException
        val companyEntity = staffEntity.companyEntity!!

        val requiredWorkTime = when (companyEntity.workSystem) {
            WorkSystem.ALTERNATIVE -> {
                val requiredWorkTimeEntity = requiredWorkTimeRepository.findByIdOrNull(companyEntity.id)!!
                GetCompanyRuleResponse.RequiredWorkTime(
                    startAt = requiredWorkTimeEntity.startAt,
                    endAt = requiredWorkTimeEntity.endAt
                )
            }
            else -> null
        }

        return GetCompanyRuleResponse(
            applyWorkHome = companyEntity.applyWorkHome,
            workSystem = companyEntity.workSystem,
            requiredWorkTime = requiredWorkTime
        )
    }
}