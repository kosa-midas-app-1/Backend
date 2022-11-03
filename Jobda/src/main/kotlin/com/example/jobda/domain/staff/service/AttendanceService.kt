package com.example.jobda.domain.staff.service

import com.example.jobda.domain.manager.exception.StaffNotFoundException
import com.example.jobda.domain.staff.WorkRecordEntity
import com.example.jobda.domain.staff.WorkRecordId
import com.example.jobda.domain.staff.repository.StaffRepository
import com.example.jobda.domain.staff.repository.WorkRecordRepository
import com.example.jobda.domain.staff.type.Status
import com.example.jobda.infrastructure.security.util.SecurityUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

/**
 *
 * AttendanceService
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Service
class AttendanceService(
    private val securityUtil: SecurityUtil,
    private val staffRepository: StaffRepository,
    private val workRecordRepository: WorkRecordRepository
) {

    fun execute() {

        val staffId = securityUtil.getCurrentUserId()
        val staffEntity = staffRepository.findByIdOrNull(staffId) ?: throw StaffNotFoundException
        val companyEntity = staffEntity.companyEntity!!

        val workRecordEntity = workRecordRepository.findByIdOrNull(
            WorkRecordId(
                date = LocalDate.now(),
                staffId = staffId,
                companyId = companyEntity.id
            )
        )

        if (workRecordEntity == null) {
            workRecordRepository.save(
                WorkRecordEntity(
                    id = WorkRecordId(
                        date = LocalDate.now(),
                        staffId = staffId,
                        companyId = companyEntity.id
                    ),
                    staffEntity = staffEntity,
                    companyEntity = companyEntity,
                    startAt = LocalDateTime.now(),
                    endAt = null,
                    isWorkHome = false
                )
            )
            staffRepository.save(staffEntity.updateStatus(Status.WORKING))
        } else {
            workRecordRepository.save(
                workRecordEntity.unAttendance()
            )
            staffRepository.save(staffEntity.updateStatus(Status.LEAVE_WORK))
        }
    }
}