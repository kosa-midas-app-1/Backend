package com.example.jobda.domain.joinRequest.service

import com.example.jobda.domain.joinRequest.repository.JoinRequestRepository
import org.springframework.stereotype.Service
import java.util.UUID

/**
 *
 * CancelJoinRequestService
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Service
class CancelJoinRequestService(
    private val joinRequestRepository: JoinRequestRepository
) {

    fun execute(staffId: UUID) {
        val joinRequestEntity = joinRequestRepository.findByIdStaffId(staffId)!!

        joinRequestRepository.delete(joinRequestEntity)
    }
}