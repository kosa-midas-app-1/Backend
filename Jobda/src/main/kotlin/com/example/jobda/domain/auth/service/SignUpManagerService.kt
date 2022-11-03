package com.example.jobda.domain.auth.service

import com.example.jobda.domain.auth.controller.dto.request.SignUpManagerRequest
import com.example.jobda.domain.auth.controller.dto.response.TokenResponse
import com.example.jobda.domain.auth.type.Authority
import com.example.jobda.domain.company.CompanyEntity
import com.example.jobda.domain.company.repository.CompanyRepository
import com.example.jobda.domain.manager.ManagerEntity
import com.example.jobda.domain.manager.repository.ManagerRepository
import com.example.jobda.infrastructure.security.jwt.JwtProvider
import org.springframework.stereotype.Service

/**
 *
 * SignUpManagerService
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Service
class SignUpManagerService(
    private val jwtProvider: JwtProvider,
    private val managerRepository: ManagerRepository,
    private val companyRepository: CompanyRepository
) {

    fun execute(request: SignUpManagerRequest): TokenResponse {

        val manager = ManagerEntity(
            email = request.email,
            password = request.password,
            phoneNumber = request.phoneNumber,
            name = request.name,
            companyEntity = null
        )

        val managerEntity = managerRepository.save(manager)

        companyRepository.save(
            CompanyEntity(
                name = request.companyName,
                profileImageUrl = request.companyProfileImageUrl,
                workSystem = request.companyWorkSystem,
                applyWorkHome = request.companyApplyWorkHome,
                businessNumber = request.companyBusinessNumber,
                managerEntity = managerEntity
            )
        )

        return jwtProvider.receiveToken(managerEntity.id, Authority.MANAGER)
    }
}