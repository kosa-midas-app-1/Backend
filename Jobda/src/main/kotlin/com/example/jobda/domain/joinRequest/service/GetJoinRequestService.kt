package com.example.jobda.domain.joinRequest.service

import com.example.jobda.domain.joinRequest.controller.dto.response.GetJoinRequestsRequest
import com.example.jobda.domain.joinRequest.repository.JoinRequestRepository
import com.example.jobda.domain.manager.exception.ManagerNotFoundException
import com.example.jobda.domain.manager.repository.ManagerRepository
import com.example.jobda.infrastructure.security.util.SecurityUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

/**
 *
 * GetJoinReqeustService
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Service
class GetJoinRequestService(
    private val joinRequestRepository: JoinRequestRepository,
    private val securityUtil: SecurityUtil,
    private val managerRepository: ManagerRepository
) {

    fun execute(): GetJoinRequestsRequest {

        val managerId = securityUtil.getCurrentUserId()
        val managerEntity = managerRepository.findByIdOrNull(managerId) ?: throw ManagerNotFoundException

        val requests = joinRequestRepository.findByIdCompanyId(managerEntity.companyEntity!!.id).map {
            val staffEntity = it.staffEntity!!
            GetJoinRequestsRequest.RequestElement(
                id = it.id.staffId,
                name = staffEntity.name,
                email = staffEntity.email,
                phoneNumber = staffEntity.phoneNumber,
                position = staffEntity.position
            )
        }

        return GetJoinRequestsRequest(requests)
    }
}