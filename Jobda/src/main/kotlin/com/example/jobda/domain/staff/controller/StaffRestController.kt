package com.example.jobda.domain.staff.controller

import com.example.jobda.domain.staff.controller.dto.request.UpdateMyInfoRequest
import com.example.jobda.domain.staff.controller.dto.request.UpdateStaffRequest
import com.example.jobda.domain.staff.controller.dto.response.AverageResponse
import com.example.jobda.domain.staff.controller.dto.response.StaffGetMyInfoResponse
import com.example.jobda.domain.staff.controller.dto.response.GetStaffDetailsResponse
import com.example.jobda.domain.staff.controller.dto.response.GetStaffListResponse
import com.example.jobda.domain.staff.controller.dto.response.GetTimeLineResponse
import com.example.jobda.domain.staff.service.AttendanceService
import com.example.jobda.domain.staff.service.AverageService
import com.example.jobda.domain.staff.service.StaffGetMyInfoService
import com.example.jobda.domain.staff.service.GetStaffDetailsService
import com.example.jobda.domain.staff.service.GetStaffListService
import com.example.jobda.domain.staff.service.GetTimeLineService
import com.example.jobda.domain.staff.service.StaffUpdateMyInfoService
import com.example.jobda.domain.staff.service.UpdateStaffInfoService
import com.example.jobda.domain.staff.service.UpdateStaffLeaveEarlyService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.validation.Valid

/**
 *
 * StaffRestController
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@RequestMapping("/staff")
@RestController
class StaffRestController(
    private val getStaffListService: GetStaffListService,
    private val getStaffDetailsService: GetStaffDetailsService,
    private val updateStaffInfoService: UpdateStaffInfoService,
    private val updateStaffLeaveEarlyService: UpdateStaffLeaveEarlyService,
    private val averageService: AverageService,
    private val getTimeListService: GetTimeLineService,

    private val getMyInfoService: StaffGetMyInfoService,
    private val updateMyInfoService: StaffUpdateMyInfoService,
    private val attendanceService: AttendanceService
) {

    @GetMapping
    fun getStaffList(name: String): GetStaffListResponse {
        return getStaffListService.execute(name)
    }

    @GetMapping("/{staff-id}")
    fun getStaffDetail(@PathVariable("staff-id") staffId: UUID): GetStaffDetailsResponse {
        return getStaffDetailsService.execute(staffId)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{staff-id}")
    fun updateStaffInfo(@PathVariable("staff-id") staffId: UUID, @RequestBody @Valid request: UpdateStaffRequest) {
        updateStaffInfoService.execute(staffId, request)
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/leave-early/{staff-id}")
    fun updateStaffLeaveEarly(@PathVariable("staff-id") staffId: UUID) {
        updateStaffLeaveEarlyService.execute(staffId)
    }

    @GetMapping("/average")
    fun getAverage(): AverageResponse {
        return averageService.execute()
    }

    @GetMapping("/timeline")
    fun getTimeLine(): GetTimeLineResponse {
        return getTimeListService.execute()
    }

    @GetMapping("/today")
    fun getTodayStaff() {

    }

    @GetMapping("/work-time")
    fun getMyWorkTime() {

    }

    @GetMapping("/work-time/week")
    fun getMyWeekWorkTime() {

    }

    @GetMapping("/myself")
    fun managerGetMyInfo(): StaffGetMyInfoResponse {
        return getMyInfoService.execute()
    }

    @PatchMapping("/myself")
    fun updateMyInfo(@RequestBody @Valid request: UpdateMyInfoRequest) {
        updateMyInfoService.execute(request)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/attendance")
    fun attendance() {
        attendanceService.execute()
    }

}