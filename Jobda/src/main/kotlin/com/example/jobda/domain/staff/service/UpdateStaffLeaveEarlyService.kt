package com.example.jobda.domain.staff.service

import com.example.jobda.domain.manager.exception.StaffNotFoundException
import com.example.jobda.domain.staff.WorkRecordId
import com.example.jobda.domain.staff.repository.StaffRepository
import com.example.jobda.domain.staff.repository.WorkRecordRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.UUID

/**
 *
 * updateStaffLeaveEarlyService
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Service
class UpdateStaffLeaveEarlyService(
    private val staffRepository: StaffRepository,
    private val workRecordRepository: WorkRecordRepository
) {

    fun execute(staffId: UUID) {
        val staffEntity = staffRepository.findByIdOrNull(staffId) ?: throw StaffNotFoundException

        staffRepository.save(staffEntity.updateStaffLeaveEarly())

        workRecordRepository.findByIdOrNull(
            WorkRecordId(
                date = LocalDate.now(),
                staffId = staffEntity.id,
                companyId = staffEntity.companyEntity!!.id
            )
        )
    }
}