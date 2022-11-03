package com.example.jobda.domain.auth.service

import com.example.jobda.domain.auth.controller.dto.request.SignInRequest
import com.example.jobda.domain.auth.controller.dto.response.TokenResponse
import com.example.jobda.domain.auth.exception.EmailNotFoundException
import com.example.jobda.domain.auth.exception.InvalidPasswordException
import com.example.jobda.domain.auth.type.Authority
import com.example.jobda.domain.manager.repository.ManagerRepository
import com.example.jobda.domain.staff.repository.StaffRepository
import com.example.jobda.infrastructure.security.jwt.JwtProvider
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

/**
 *
 * SignInService
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Service
class SignInService(
    private val jwtProvider: JwtProvider,
    private val staffRepository: StaffRepository,
    private val managerRepository: ManagerRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) {

    fun execute(request: SignInRequest): TokenResponse{

        val userId: UUID
        val password: String
        val authority: Authority

        staffRepository.findByEmail(request.email).let {
            if (it != null) {
                userId = it.id
                password = it.password
                authority = Authority.STAFF
            } else {
                managerRepository.findByEmail(request.email).let {
                    if (it != null) {
                        userId = it.id
                        password = it.password
                        authority = Authority.MANAGER
                    } else {
                        throw EmailNotFoundException
                    }
                }
            }
        }

        if (!passwordEncoder.matches(request.password, password)) throw InvalidPasswordException

        return jwtProvider.receiveToken(userId, authority)
    }

}