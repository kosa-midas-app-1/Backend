package com.example.jobda.domain.joinRequest.service

import com.example.jobda.domain.company.exception.JoinRequestAlreadyExistsException
import com.example.jobda.domain.company.repository.CompanyRepository
import com.example.jobda.domain.joinRequest.JoinRequestEntity
import com.example.jobda.domain.joinRequest.JoinRequestId
import com.example.jobda.domain.joinRequest.repository.JoinRequestRepository
import com.example.jobda.domain.manager.exception.StaffNotFoundException
import com.example.jobda.domain.staff.repository.StaffRepository
import com.example.jobda.infrastructure.security.util.SecurityUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

/**
 *
 * ReJoinRequestService
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Service
class ReJoinRequestService(
    private val securityUtil: SecurityUtil,
    private val staffRepository: StaffRepository,
    private val companyRepository: CompanyRepository,
    private val joinRequestRepository: JoinRequestRepository
) {

    fun execute(company: UUID) {

        val staffId = securityUtil.getCurrentUserId()

        if (joinRequestRepository.findByIdStaffId(staffId) != null) {
            throw JoinRequestAlreadyExistsException
        }

        val staffEntity = staffRepository.findByIdOrNull(staffId) ?: throw StaffNotFoundException

        val companyEntity = companyRepository.findByIdOrNull(company) ?: throw JoinRequestAlreadyExistsException

        val joinRequest = JoinRequestEntity(
            JoinRequestId(
                staffId = staffEntity.id,
                companyId = companyEntity.id
            ),
            staffEntity = staffEntity,
            companyEntity = companyEntity
        )

        joinRequestRepository.save(joinRequest)
    }
}