package com.example.jobda.domain.manager.controller

import com.example.jobda.domain.manager.controller.dto.request.UpdateMyInfoRequest
import com.example.jobda.domain.manager.controller.dto.response.GetMyInfoResponse
import com.example.jobda.domain.manager.service.GetMyInfoService
import com.example.jobda.domain.manager.service.UpdateMyInfoService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 *
 * ManagerRestController
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@RestController
class ManagerRestController(
    private val updateMyInfoService: UpdateMyInfoService,
    private val getMyInfoService: GetMyInfoService
) {

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    fun updateMyInfo(@RequestBody @Valid request: UpdateMyInfoRequest) {
        updateMyInfoService.execute(request)
    }


    @GetMapping
    fun getMyInfo(): GetMyInfoResponse {
        return getMyInfoService.execute()
    }
}