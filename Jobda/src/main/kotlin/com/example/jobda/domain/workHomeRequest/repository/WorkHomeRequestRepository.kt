package com.example.jobda.domain.workHomeRequest.repository

import com.example.jobda.domain.workHomeRequest.WorkHomeRequestEntity
import com.example.jobda.domain.workHomeRequest.WorkHomeRequestId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 *
 * WorkHomeRequestRepository
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
@Repository
interface WorkHomeRequestRepository : CrudRepository<WorkHomeRequestEntity, WorkHomeRequestId> {
}