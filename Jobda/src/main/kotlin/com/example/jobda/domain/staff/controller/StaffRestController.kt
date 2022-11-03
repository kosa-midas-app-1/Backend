package com.example.jobda.domain.staff.controller

import com.example.jobda.domain.staff.controller.dto.response.GetStaffDetailsResponse
import com.example.jobda.domain.staff.controller.dto.response.GetStaffListResponse
import com.example.jobda.domain.staff.service.GetStaffDetailsService
import com.example.jobda.domain.staff.service.GetStaffListService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

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
    private val getStaffDetailsService: GetStaffDetailsService
) {

    @GetMapping
    fun getStaffList(name: String): GetStaffListResponse {
        return getStaffListService.execute(name)
    }

    @GetMapping("/{staff-id}")
    fun getStaffDetail(@PathVariable("staff-id") staffId: UUID): GetStaffDetailsResponse {
        return getStaffDetailsService.execute(staffId)
    }

    @PatchMapping("/{staff-id}")
    fun updateStaffInfo(@PathVariable("staff-id") staffId: UUID) {

    }

    @PatchMapping("/leave-early/{staff-id}")
    fun updateStaffLeaveEarly(@PathVariable("staff-id") staffId: UUID) {

    }

    @GetMapping("/average")
    fun getAverage() {

    }

    @GetMapping("/timeline")
    fun getTimeLine() {

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
    fun getMyInfo() {

    }

    @PatchMapping("/myself")
    fun updateMyInfo() {

    }

}