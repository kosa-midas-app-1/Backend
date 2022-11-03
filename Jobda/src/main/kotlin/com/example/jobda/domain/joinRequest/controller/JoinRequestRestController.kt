package com.example.jobda.domain.joinRequest.controller

import com.example.jobda.domain.joinRequest.controller.dto.response.GetJoinRequestsRequest
import com.example.jobda.domain.joinRequest.service.ApproveJoinRequestService
import com.example.jobda.domain.joinRequest.service.CancelJoinRequestService
import com.example.jobda.domain.joinRequest.service.GetJoinRequestService
import com.example.jobda.domain.joinRequest.service.ReJoinRequestService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

/**
 *
 * JoinRequestRestController
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@RequestMapping("/join-requests")
@RestController
class JoinRequestRestController(
    private val getJoinRequestService: GetJoinRequestService,
    private val approveJoinRequestService: ApproveJoinRequestService,
    private val cancelJoinRequestService: CancelJoinRequestService,
    private val reJoinRequestService: ReJoinRequestService
) {

    @GetMapping
    fun getJoinRequest(): GetJoinRequestsRequest {
        return getJoinRequestService.execute()
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{staff-id}")
    fun approveJoinRequest(@PathVariable("staff-id") staffId: UUID) {
        approveJoinRequestService.execute(staffId)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{staff-id}")
    fun cancelJoinRequest(@PathVariable("staff-id") staffId: UUID) {
        cancelJoinRequestService.execute(staffId)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{company-id}")
    fun reJoinRequest(@PathVariable("company-id") companyId: UUID) {
        reJoinRequestService.execute(companyId)
    }
}