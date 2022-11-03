package com.example.jobda.domain.auth.service

import com.example.jobda.domain.auth.controller.dto.request.SignUpStaffRequest
import com.example.jobda.domain.auth.controller.dto.response.TokenResponse
import com.example.jobda.domain.auth.type.Authority
import com.example.jobda.domain.company.exception.CompanyNotFoundException
import com.example.jobda.domain.company.repository.CompanyRepository
import com.example.jobda.domain.staff.StaffEntity
import com.example.jobda.domain.staff.repository.StaffRepository
import com.example.jobda.domain.staff.type.Status
import com.example.jobda.infrastructure.security.jwt.JwtProvider
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

/**
 *
 * SignUpStaffService
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Service
class SignUpStaffService(
    private val jwtProvider: JwtProvider,
    private val staffRepository: StaffRepository,
    private val companyRepository: CompanyRepository
) {

    fun execute(request: SignUpStaffRequest): TokenResponse {

        val companyEntity = companyRepository.findByIdOrNull(request.companyId) ?: throw CompanyNotFoundException

        val staff = StaffEntity(
            email = request.email,
            password = request.password,
            phoneNumber = request.phoneNumber,
            name = request.name,
            position = request.position,
            status = Status.NO_DATA,
            companyEntity = companyEntity
        )

        val staffEntity = staffRepository.save(staff)

        return jwtProvider.receiveToken(staffEntity.id, Authority.STAFF)
    }
}