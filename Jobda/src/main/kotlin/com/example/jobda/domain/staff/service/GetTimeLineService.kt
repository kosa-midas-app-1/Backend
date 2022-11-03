package com.example.jobda.domain.staff.service

import com.example.jobda.domain.manager.exception.ManagerNotFoundException
import com.example.jobda.domain.manager.repository.ManagerRepository
import com.example.jobda.domain.staff.controller.dto.response.GetTimeLineResponse
import com.example.jobda.domain.staff.repository.StaffRepository
import com.example.jobda.domain.staff.repository.WorkRecordRepository
import com.example.jobda.infrastructure.security.util.SecurityUtil
import com.example.jobda.infrastructure.security.util.TimeUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate

/**
 *
 * GetTimeLineService
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Service
class GetTimeLineService(
    private val securityUtil: SecurityUtil,
    private val managerRepository: ManagerRepository,
    private val staffRepository: StaffRepository,
    private val workRecordRepository: WorkRecordRepository,
    private val timeUtil: TimeUtil
) {

    fun execute(): GetTimeLineResponse {

        val managerId = securityUtil.getCurrentUserId()
        val managerEntity = managerRepository.findByIdOrNull(managerId) ?: throw ManagerNotFoundException
        val staffEntityList = staffRepository.findByCompanyEntityId(managerEntity.companyEntity!!.id)

        val timeList = mutableListOf<Int>()

        for (i: Int in 1..24) timeList.set(i, 0)

        staffEntityList.map {
            val workRecordEntity = workRecordRepository.findByIdCompanyIdAndIdStaffIdAndIdDateBetween(
                companyId = managerEntity.companyEntity!!.id,
                staffId = it.id,
                startAt = LocalDate.now(),
                endAt = LocalDate.now()
            )

            for (i: Int in 1..24) {
                val response = workRecordEntity.filter {
                    it.startAt.hour >= i && it.endAt!!.hour <= i
                }
                timeList[i] += response.count()
            }
            true
        }

        var i = 1
        return GetTimeLineResponse(timeList.map {
            GetTimeLineResponse.HourElement(
                i++,
                it
            )
        })
    }
}