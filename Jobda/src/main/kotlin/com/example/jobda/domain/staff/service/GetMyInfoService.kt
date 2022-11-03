package com.example.jobda.domain.staff.service

import com.example.jobda.domain.manager.exception.StaffNotFoundException
import com.example.jobda.domain.staff.controller.dto.response.GetMyInfoResponse
import com.example.jobda.domain.staff.repository.StaffRepository
import com.example.jobda.infrastructure.security.util.SecurityUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

/**
 *
 * GetMyInfoService
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Service
class GetMyInfoService(
    private val securityUtil: SecurityUtil,
    private val staffRepository: StaffRepository
) {

    fun execute(): GetMyInfoResponse {

        val staffId = securityUtil.getCurrentUserId()
        val staffEntity = staffRepository.findByIdOrNull(staffId) ?: throw StaffNotFoundException
        val companyEntity = staffEntity.companyEntity
            ?: return GetMyInfoResponse(
                name = staffEntity.name,
                phoneNumber = staffEntity.phoneNumber,
                email = staffEntity.email,
                companyName = null,
                position = null
            )

        return GetMyInfoResponse(
            name = staffEntity.name,
            phoneNumber = staffEntity.phoneNumber,
            email = staffEntity.email,
            companyName = companyEntity.name,
            position = staffEntity.position
        )
    }
}