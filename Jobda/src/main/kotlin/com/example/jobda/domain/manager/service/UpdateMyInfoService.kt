package com.example.jobda.domain.manager.service

import com.example.jobda.domain.manager.controller.dto.request.UpdateMyInfoRequest
import com.example.jobda.domain.manager.exception.ManagerNotFoundException
import com.example.jobda.domain.manager.repository.ManagerRepository
import com.example.jobda.infrastructure.security.util.SecurityUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

/**
 *
 * UpdateMyInfoService
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Service
class UpdateMyInfoService(
    private val securityUtil: SecurityUtil,
    private val managerRepository: ManagerRepository
) {

    fun execute(request: UpdateMyInfoRequest) {

        val managerId = securityUtil.getCurrentUserId()
        val managerEntity = managerRepository.findByIdOrNull(managerId) ?: throw ManagerNotFoundException

        managerRepository.save(
            managerEntity.updateMyInfo(
                email = request.email,
                phoneNumber = request.phoneNumber,
                name = request.name
            )
        )
    }
}