package com.example.jobda.domain.staff.service

import com.example.jobda.domain.manager.exception.StaffNotFoundException
import com.example.jobda.domain.staff.WorkRecordId
import com.example.jobda.domain.staff.controller.dto.response.GetStaffDetailsResponse
import com.example.jobda.domain.staff.repository.StaffRepository
import com.example.jobda.domain.staff.repository.WorkRecordRepository
import com.example.jobda.infrastructure.security.util.TimeUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

/**
 *
 * GetStaffDetailsService
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Service
class GetStaffDetailsService(
    private val staffRepository: StaffRepository,
    private val workRecordRepository: WorkRecordRepository,
    private val timeUtil: TimeUtil
) {

    fun execute(staffId: UUID): GetStaffDetailsResponse {

        val staff = staffRepository.findByIdOrNull(staffId) ?: throw StaffNotFoundException

        val now = LocalDate.now()
        val nowDay = LocalDate.now().dayOfWeek.value

        val workTodayRecord = workRecordRepository.findByIdOrNull(
            WorkRecordId(
                date = now,
                staffId = staffId,
                companyId = staff.companyEntity!!.id
            )
        )

        val startAt = workTodayRecord.let {
            it?.startAt ?: null
        }

        val endAt = workTodayRecord.let {
            it?.endAt ?: null
        }

        val todayWorkTime = workTodayRecord.let {
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

        val workWeekRecord = workRecordRepository.findByIdCompanyIdAndIdStaffIdAndIdDateBetween(
            companyId = staff.companyEntity!!.id,
            staffId = staffId,
            startAt = now.minusDays(nowDay.toLong()-1),
            endAt = now.plusDays(7-nowDay.toLong())
        )


        var totalWork = 0L
        var averageStart = 0

        for (i: Int in workWeekRecord.indices) {
            totalWork += timeUtil.betweenForMinutes(workWeekRecord[i].startAt, workWeekRecord[i].endAt!!)
            averageStart += workWeekRecord[i].startAt.hour * 60 + workWeekRecord[i].startAt.minute
        }


        return GetStaffDetailsResponse(
            id = staff.id,
            name = staff.name,
            position = staff.position,
            email = staff.email,
            phoneNumber = staff.phoneNumber,
            startAt = startAt,
            endAt = endAt,
            totalWork = todayWorkTime.toInt(),
            todayWork = totalWork.toInt(),
            averageWork = totalWork.toInt()/workWeekRecord.size,
            averageStart = LocalDateTime.of(now.year, now.month, now.dayOfMonth, averageStart/workWeekRecord.size/60, averageStart/workWeekRecord.size%60)
        )
    }
}