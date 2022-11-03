package com.example.jobda.domain.company

import com.example.jobda.common.BaseUUIDEntity
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

/**
 *
 * CompanyEntity
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
@Entity
@Table(name = "tbl_company")
class CompanyEntity(

    override val id: UUID,

    @field:Column(columnDefinition = "VARCHAR(40)", nullable = false)
    val name: String,

    @field:Column(columnDefinition = "VARCHAR(255)", nullable = false)
    val profileImageUrl: String,

    @field:Column(columnDefinition = "VARCHAR(11)", nullable = false)
    val workSystem: String,

    @field:Column(columnDefinition = "TINYINT(1)", nullable = false)
    val applyWorkHome: Boolean,

    @field:Column(columnDefinition = "VARCHAR(16)", nullable = false)
    val businessNumber: String,

    //TODO 관리자 아이디

) : BaseUUIDEntity() {
}