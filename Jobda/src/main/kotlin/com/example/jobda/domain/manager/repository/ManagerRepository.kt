package com.example.jobda.domain.manager.repository

import com.example.jobda.domain.manager.ManagerEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

/**
 *
 * ManagerRepository
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
@Repository
interface ManagerRepository : CrudRepository<ManagerEntity, UUID> {
    fun findByEmail(email: String): ManagerEntity?
}