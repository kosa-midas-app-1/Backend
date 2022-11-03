package com.example.jobda.domain.auth.service

import com.example.jobda.domain.auth.controller.dto.request.SignUpManagerRequest
import com.example.jobda.domain.auth.controller.dto.response.TokenResponse
import com.example.jobda.domain.auth.type.Authority
import com.example.jobda.domain.company.CompanyEntity
import com.example.jobda.domain.company.RequiredWorkTimeEntity
import com.example.jobda.domain.company.repository.CompanyRepository
import com.example.jobda.domain.company.repository.RequiredWorkTimeRepository
import com.example.jobda.domain.manager.ManagerEntity
import com.example.jobda.domain.manager.repository.ManagerRepository
import com.example.jobda.infrastructure.security.jwt.JwtProvider
import org.springframework.stereotype.Service
import java.time.LocalDateTime

/**
 *
 * SignUpManagerService
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 2.0.0
 **/
@Service
class SignUpManagerService(
    private val jwtProvider: JwtProvider,
    private val managerRepository: ManagerRepository,
    private val companyRepository: CompanyRepository,
    private val requiredWorkTimeRepository: RequiredWorkTimeRepository
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

        val companyEntity = companyRepository.save(
            CompanyEntity(
                name = request.companyName,
                profileImageUrl = request.companyProfileImageUrl,
                workSystem = request.companyWorkSystem,
                applyWorkHome = request.companyApplyWorkHome,
                businessNumber = request.companyBusinessNumber,
                managerEntity = managerEntity
            )
        )

        val requiredWorkTime = RequiredWorkTimeEntity(
            companyId = companyEntity.id,
            companyEntity = companyEntity,
            startAt = LocalDateTime.of(2022, 11, 4, 14, 0),
            endAt = LocalDateTime.of(2022, 11, 4, 15, 0)
        )

        requiredWorkTimeRepository.save(requiredWorkTime)

        return jwtProvider.receiveToken(managerEntity.id, Authority.MANAGER)
    }
}