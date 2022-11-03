package com.example.jobda.domain.staff.service

import com.example.jobda.domain.manager.exception.ManagerNotFoundException
import com.example.jobda.domain.manager.repository.ManagerRepository
import com.example.jobda.domain.staff.WorkRecordId
import com.example.jobda.domain.staff.controller.dto.response.GetStaffListResponse
import com.example.jobda.domain.staff.repository.StaffRepository
import com.example.jobda.domain.staff.repository.WorkRecordRepository
import com.example.jobda.infrastructure.security.util.SecurityUtil
import com.example.jobda.infrastructure.security.util.TimeUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

/**
 *
 * GetStaffListService
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Service
class GetStaffListService(
    private val securityUtil: SecurityUtil,
    private val managerRepository: ManagerRepository,
    private val staffRepository: StaffRepository,
    private val workRecordRepository: WorkRecordRepository,
    private val timeUtil: TimeUtil
) {

    //TODO 리팩토링 (해커톤의 한계..)
    fun execute(name: String): GetStaffListResponse {

        val managerId = securityUtil.getCurrentUserId()
        val managerEntity = managerRepository.findByIdOrNull(managerId) ?: throw ManagerNotFoundException
        val companyEntity = managerEntity!!.companyEntity!!

        val staff = staffRepository.findByCompanyEntityIdAndNameContaining(companyEntity.id, name).map { it ->
            val todayWorkTimeEntity = workRecordRepository.findByIdOrNull(
                WorkRecordId(
                    date = LocalDate.now(),
                    companyId = companyEntity.id,
                    staffId = it.id
                )
            )
            val todayWorkTime = todayWorkTimeEntity.let {
                if (it != null) {
                    if (it.endAt != null) {
                        timeUtil.betweenForMinutes(it.startAt, it.endAt!!)
                    } else {
                        timeUtil.betweenForMinutes(it.startAt, LocalDateTime.now())
                    }
                } else {
                    0
                }
            }

            GetStaffListResponse.StaffElement(
                id = it.id,
                name = it.name,
                position = it.position,
                status = it.status,
                todayWorkTime = todayWorkTime.toInt()
            )
        }

        return GetStaffListResponse(staff)
    }
}