package com.example.jobda.domain.staff.service

import com.example.jobda.domain.manager.exception.ManagerNotFoundException
import com.example.jobda.domain.manager.repository.ManagerRepository
import com.example.jobda.domain.staff.controller.dto.response.AverageResponse
import com.example.jobda.domain.staff.repository.StaffRepository
import com.example.jobda.domain.staff.repository.WorkRecordRepository
import com.example.jobda.infrastructure.security.util.SecurityUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

/**
 *
 * AverageService
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Service
class AverageService(
    private val securityUtil: SecurityUtil,
    private val managerRepository: ManagerRepository,
    private val staffRepository: StaffRepository,
    private val workRecordRepository: WorkRecordRepository,
) {

    fun execute(): AverageResponse {

        val managerId = securityUtil.getCurrentUserId()
        val managerEntity = managerRepository.findByIdOrNull(managerId) ?: throw ManagerNotFoundException

        var averageEnd = 0
        var averageStart = 0
        var totalCount = 0

        staffRepository.findByCompanyEntityId(managerEntity.companyEntity!!.id)
            .map {
                val workTotalRecord = workRecordRepository.findByIdCompanyIdAndIdStaffId(
                    companyId = it.companyEntity!!.id,
                    staffId = it.id
                )

                totalCount += workTotalRecord.size

                for (i: Int in workTotalRecord.indices) {
                    val end = workTotalRecord[i].endAt ?: LocalDateTime.of(0, 0, 0, 0, 0)
                    averageEnd += end.hour * 60 + workTotalRecord[i].startAt.minute
                    averageStart += workTotalRecord[i].startAt.hour * 60 + workTotalRecord[i].startAt.minute
                }
            }

        return AverageResponse(
            averageStartAt = averageStart,
            averageEndAt = averageEnd
        )
    }
}