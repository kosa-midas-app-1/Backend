package com.example.jobda.domain.staff.service

import com.example.jobda.domain.manager.exception.StaffNotFoundException
import com.example.jobda.domain.staff.controller.dto.request.UpdateMyInfoRequest
import com.example.jobda.domain.staff.repository.StaffRepository
import com.example.jobda.infrastructure.security.util.SecurityUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

/**
 *
 * UpdateMyInfoService
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Service
class StaffUpdateMyInfoService(
    private val securityUtil: SecurityUtil,
    private val staffRepository: StaffRepository
) {

    fun execute(request: UpdateMyInfoRequest) {

        val staffId = securityUtil.getCurrentUserId()
        val staffEntity = staffRepository.findByIdOrNull(staffId) ?: throw StaffNotFoundException

        staffRepository.save(
            staffEntity.updateStaffInfo(
                email = request.email,
                phoneNumber = request.phoneNumber,
                position = request.position
            )
        )
    }
}