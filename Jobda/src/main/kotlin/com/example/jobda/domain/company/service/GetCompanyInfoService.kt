package com.example.jobda.domain.company.service

import com.example.jobda.domain.company.controller.dto.response.GetCompanyInfoResponse
import com.example.jobda.domain.company.repository.RequiredWorkTimeRepository
import com.example.jobda.domain.company.type.WorkSystem
import com.example.jobda.domain.manager.repository.ManagerRepository
import com.example.jobda.domain.staff.repository.StaffRepository
import com.example.jobda.infrastructure.security.util.SecurityUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

/**
 *
 * GetCompanyInfoService
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Service
class GetCompanyInfoService(
    private val securityUtil: SecurityUtil,
    private val managerRepository: ManagerRepository,
    private val staffRepository: StaffRepository,
    private val requiredWorkTimeRepository: RequiredWorkTimeRepository
) {

    fun execute(): GetCompanyInfoResponse {

        val userId = securityUtil.getCurrentUserId()

        val companyEntity = managerRepository.findByIdOrNull(userId).let {
            if (it == null) {
                staffRepository.findByIdOrNull(userId)!!.companyEntity!!
            } else {
                it.companyEntity!!
            }
        }

        val staffCount = staffRepository.countByCompanyEntityId(companyEntity.id)

        val requiredWorkTime = when (companyEntity.workSystem) {
            WorkSystem.ALTERNATIVE -> {
                val requiredWorkTimeEntity = requiredWorkTimeRepository.findByIdOrNull(companyEntity.id)!!
                GetCompanyInfoResponse.RequiredWorkTime(
                    startAt = requiredWorkTimeEntity.startAt,
                    endAt = requiredWorkTimeEntity.endAt
                )
            }
            else -> null
        }

        return GetCompanyInfoResponse(
            profileImageUrl = companyEntity.profileImageUrl,
            name = companyEntity.name,
            businessNumber = companyEntity.businessNumber,
            staffCount = staffCount,
            applyWorkHome = companyEntity.applyWorkHome,
            workSystem = companyEntity.workSystem,
            requiredWorkTime = requiredWorkTime
        )
    }
}