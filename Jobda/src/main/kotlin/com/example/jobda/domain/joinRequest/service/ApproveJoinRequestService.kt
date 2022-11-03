package com.example.jobda.domain.joinRequest.service

import com.example.jobda.domain.joinRequest.repository.JoinRequestRepository
import com.example.jobda.domain.manager.exception.StaffNotFoundException
import com.example.jobda.domain.manager.repository.ManagerRepository
import com.example.jobda.domain.staff.repository.StaffRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

/**
 *
 * ApproveJoinRequestService
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Service
class ApproveJoinRequestService(
    private val joinRequestRepository: JoinRequestRepository,
    private val staffRepository: StaffRepository
) {

    fun execute(staffId: UUID) {
        val joinRequestEntity = joinRequestRepository.findByIdStaffId(staffId)!!
        val companyEntity = joinRequestEntity.companyEntity!!

        joinRequestRepository.delete(joinRequestEntity)

        val staffEntity = staffRepository.findByIdOrNull(staffId) ?: throw StaffNotFoundException

        staffRepository.save(staffEntity.approveJoinRequest(companyEntity))
    }
}