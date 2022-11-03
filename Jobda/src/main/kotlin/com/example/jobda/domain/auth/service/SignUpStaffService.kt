package com.example.jobda.domain.auth.service

import com.example.jobda.domain.auth.controller.dto.request.SignUpStaffRequest
import com.example.jobda.domain.auth.controller.dto.response.TokenResponse
import com.example.jobda.domain.auth.exception.EmailAlreadyExistsException
import com.example.jobda.domain.auth.type.Authority
import com.example.jobda.domain.company.exception.CompanyNotFoundException
import com.example.jobda.domain.company.repository.CompanyRepository
import com.example.jobda.domain.joinRequest.JoinRequestEntity
import com.example.jobda.domain.joinRequest.JoinRequestId
import com.example.jobda.domain.joinRequest.repository.JoinRequestRepository
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
 * @version 2.0.0
 **/
@Service
class SignUpStaffService(
    private val jwtProvider: JwtProvider,
    private val staffRepository: StaffRepository,
    private val companyRepository: CompanyRepository,
    private val joinRequestRepository: JoinRequestRepository
) {

    fun execute(request: SignUpStaffRequest): TokenResponse {

        val companyEntity = companyRepository.findByIdOrNull(request.companyId) ?: throw CompanyNotFoundException

        if (staffRepository.findByEmail(request.email) != null) {
            throw EmailAlreadyExistsException
        }

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

        val joinRequest = JoinRequestEntity(
            JoinRequestId(
                staffId = staffEntity.id,
                companyId = companyEntity.id
            ),
            staffEntity = staffEntity,
            companyEntity = companyEntity
        )

        joinRequestRepository.save(joinRequest)

        return jwtProvider.receiveToken(staffEntity.id, Authority.STAFF)
    }
}