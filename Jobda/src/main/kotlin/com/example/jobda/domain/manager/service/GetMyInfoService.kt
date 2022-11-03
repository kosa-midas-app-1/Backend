package com.example.jobda.domain.manager.service

import com.example.jobda.domain.manager.controller.dto.response.GetMyInfoResponse
import com.example.jobda.domain.manager.exception.ManagerNotFoundException
import com.example.jobda.domain.manager.repository.ManagerRepository
import com.example.jobda.infrastructure.security.util.SecurityUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

/**
 *
 * GetMyInfoService
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Service
class GetMyInfoService(
    private val securityUtil: SecurityUtil,
    private val managerRepository: ManagerRepository
) {

    fun execute(): GetMyInfoResponse {

        val managerId = securityUtil.getCurrentUserId()
        val managerEntity = managerRepository.findByIdOrNull(managerId) ?: throw ManagerNotFoundException

        return GetMyInfoResponse(
            email = managerEntity.email,
            phoneNumber = managerEntity.email,
            name = managerEntity.name
        )
    }
}