package com.example.jobda.domain.company.service

import com.example.jobda.domain.company.controller.dto.request.UpdateCompanyInfoRequest
import com.example.jobda.domain.company.repository.CompanyRepository
import com.example.jobda.domain.manager.exception.StaffNotFoundException
import com.example.jobda.domain.manager.repository.ManagerRepository
import com.example.jobda.infrastructure.security.util.SecurityUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

/**
 *
 * UpdateCompanyInfoService
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Service
class UpdateCompanyInfoService(
    private val securityUtil: SecurityUtil,
    private val managerRepository: ManagerRepository,
    private val companyRepository: CompanyRepository
) {

    fun execute(request: UpdateCompanyInfoRequest) {

        val managerId = securityUtil.getCurrentUserId()
        val managerEntity = managerRepository.findByIdOrNull(managerId) ?: throw StaffNotFoundException
        val companyEntity = managerEntity.companyEntity!!

        companyRepository.save(
            companyEntity.updateCompanyInfo(
                name = request.name,
                profileImageUrl = request.profileImageUrl,
                workSystem = request.workSystem,
                applyWorkHome = request.applyWorkHome
            )
        )
    }
}