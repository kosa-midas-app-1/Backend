package com.example.jobda.domain.auth.controller

import com.example.jobda.domain.auth.controller.dto.request.SignInRequest
import com.example.jobda.domain.auth.controller.dto.request.SignUpManagerRequest
import com.example.jobda.domain.auth.controller.dto.request.SignUpStaffRequest
import com.example.jobda.domain.auth.controller.dto.response.TokenResponse
import com.example.jobda.domain.auth.service.ReissueTokenService
import com.example.jobda.domain.auth.service.SignInService
import com.example.jobda.domain.auth.service.SignUpManagerService
import com.example.jobda.domain.auth.service.SignUpStaffService
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 *
 * AuthRestController
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@RestController
class AuthRestController(
    private val singUpManagerService: SignUpManagerService,
    private val singUpStaffService: SignUpStaffService,
    private val signInService: SignInService,
    private val reissueTokenService: ReissueTokenService
) {

    @PostMapping("/managers")
    fun singUpManager(@RequestBody @Valid request: SignUpManagerRequest): TokenResponse {
        return singUpManagerService.execute(request)
    }

    @PostMapping("/staff")
    fun signUpStaff(@RequestBody @Valid request: SignUpStaffRequest): TokenResponse {
        return singUpStaffService.execute(request)
    }

    @PostMapping
    fun signIn(@RequestBody @Valid request: SignInRequest): TokenResponse {
        return signInService.execute(request)
    }

    @PatchMapping
    fun reissueToken(@RequestHeader("Refresh-Token") refreshToken: String): TokenResponse {
        return reissueTokenService.execute(refreshToken)
    }
}