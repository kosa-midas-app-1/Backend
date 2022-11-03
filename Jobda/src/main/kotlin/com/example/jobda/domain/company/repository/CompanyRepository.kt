package com.example.jobda.domain.company.repository

import com.example.jobda.domain.company.CompanyEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

/**
 *
 * CompanyRepository
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
@Repository
interface CompanyRepository : CrudRepository<CompanyEntity, UUID> {
}