package com.example.jobda.domain.company

import com.example.jobda.common.BaseUUIDEntity
import com.example.jobda.domain.company.type.WorkSystem
import com.example.jobda.domain.manager.ManagerEntity
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.persistence.Table

/**
 *
 * CompanyEntity
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.4.2
 **/
@Entity
@Table(name = "tbl_company")
class CompanyEntity(

    override val id: UUID = UUID(0, 0),

    @field:Column(columnDefinition = "VARCHAR(40)", nullable = false)
    val name: String,

    @field:Column(columnDefinition = "VARCHAR(255)", nullable = false)
    val profileImageUrl: String,

    @field:Enumerated(EnumType.STRING)
    @field:Column(columnDefinition = "VARCHAR(11)", nullable = false)
    val workSystem: WorkSystem,

    @field:Column(columnDefinition = "TINYINT(1)", nullable = false)
    val applyWorkHome: Boolean,

    @field:Column(columnDefinition = "VARCHAR(16)", nullable = false)
    val businessNumber: String,

    @field:OneToOne(fetch = FetchType.LAZY, mappedBy = "companyEntity")
    @field:JoinColumn(name = "manager_id",columnDefinition = "BINARY(16)", nullable = false)
    val managerEntity: ManagerEntity?

) : BaseUUIDEntity() {

    fun updateCompanyInfo(name: String, profileImageUrl: String, workSystem: WorkSystem, applyWorkHome: Boolean): CompanyEntity {
        return CompanyEntity(
            id = id,
            name = name,
            profileImageUrl = profileImageUrl,
            workSystem = workSystem,
            applyWorkHome = applyWorkHome,
            businessNumber = businessNumber,
            managerEntity = managerEntity
        )
    }
}