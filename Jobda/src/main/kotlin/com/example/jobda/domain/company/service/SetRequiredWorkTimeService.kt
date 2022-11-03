package com.example.jobda.domain.company.service

import com.example.jobda.domain.company.RequiredWorkTimeEntity
import com.example.jobda.domain.company.controller.dto.request.SetRequiredWorkTimeRequest
import com.example.jobda.domain.company.repository.RequiredWorkTimeRepository
import com.example.jobda.domain.manager.exception.StaffNotFoundException
import com.example.jobda.domain.manager.repository.ManagerRepository
import com.example.jobda.infrastructure.security.util.SecurityUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

/**
 *
 * SetRequiredWorkTimeService
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Service
class SetRequiredWorkTimeService(
    private val securityUtil: SecurityUtil,
    private val managerRepository: ManagerRepository,
    private val requiredWorkTimeRepository: RequiredWorkTimeRepository
) {

    fun execute(request: SetRequiredWorkTimeRequest) {

        val managerId = securityUtil.getCurrentUserId()
        val managerEntity = managerRepository.findByIdOrNull(managerId) ?: throw StaffNotFoundException
        val companyEntity = managerEntity.companyEntity!!

        val requiredWorkTime = RequiredWorkTimeEntity(
            companyId = companyEntity.id,
            companyEntity = companyEntity,
            startAt = request.startAt,
            endAt = request.endAt
        )

        requiredWorkTimeRepository.save(requiredWorkTime)
    }
}