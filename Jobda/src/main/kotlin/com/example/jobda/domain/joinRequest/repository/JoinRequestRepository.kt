package com.example.jobda.domain.joinRequest.repository

import com.example.jobda.domain.joinRequest.JoinRequestEntity
import com.example.jobda.domain.joinRequest.JoinRequestId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

/**
 *
 * JoinRequestRepository
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
@Repository
interface JoinRequestRepository : CrudRepository<JoinRequestEntity, JoinRequestId> {
    fun findByIdCompanyId(companyId: UUID): List<JoinRequestEntity>
    fun findByIdStaffId(staffId: UUID): JoinRequestEntity?
}