package com.example.jobda.domain.manager

import com.example.jobda.common.BaseUUIDEntity
import com.example.jobda.domain.company.CompanyEntity
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.persistence.Table

/**
 *
 * ManagerEntity
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.3.0
 **/
@Entity
@Table(name = "tbl_manager")
class ManagerEntity(

    override val id: UUID = UUID(0, 0),

    @field:Column(columnDefinition = "VARCHAR(255)", nullable = false)
    val email: String,

    @field:Column(columnDefinition = "VARCHAR(255)", nullable = false)
    val password: String,

    @field:Column(columnDefinition = "VARCHAR(11)", nullable = false)
    val phoneNumber: String,

    @field:Column(columnDefinition = "VARCHAR(5)", nullable = false)
    val name: String,

    @field:OneToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "company_id",columnDefinition = "BINARY(16)")
    val companyEntity: CompanyEntity?

) : BaseUUIDEntity() {
}