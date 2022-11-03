package com.example.jobda.domain.staff.repository

import com.example.jobda.domain.staff.WorkRecordEntity
import com.example.jobda.domain.staff.WorkRecordId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 *
 * WorkRecordRepository
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
@Repository
interface WorkRecordRepository : CrudRepository<WorkRecordEntity, WorkRecordId> {
}