package com.example.jobda.domain.staff.repository

import com.example.jobda.domain.staff.StaffEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

/**
 *
 * StaffRepository
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
@Repository
interface StaffRepository : CrudRepository<StaffEntity, UUID> {
    fun findByEmail(email: String): StaffEntity?
}