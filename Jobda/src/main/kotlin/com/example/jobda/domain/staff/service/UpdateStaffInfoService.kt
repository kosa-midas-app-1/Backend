package com.example.jobda.domain.staff.service

import com.example.jobda.domain.manager.exception.StaffNotFoundException
import com.example.jobda.domain.staff.controller.dto.request.UpdateStaffRequest
import com.example.jobda.domain.staff.repository.StaffRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

/**
 *
 * UpdateStaffInfoService
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Service
class UpdateStaffInfoService(
    private val staffRepository: StaffRepository
) {

    fun execute(staffId:UUID, request: UpdateStaffRequest) {
        val staffEntity = staffRepository.findByIdOrNull(staffId) ?: throw StaffNotFoundException

        staffRepository.save(staffEntity)
    }

}