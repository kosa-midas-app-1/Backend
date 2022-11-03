package com.example.jobda.domain.staff

import com.example.jobda.common.BaseUUIDEntity
import com.example.jobda.domain.staff.type.Status
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table

/**
 *
 * StaffEntity
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
@Entity
@Table(name = "tbl_staff")
class StaffEntity(

    @field:Column(columnDefinition = "VARCHAR(255)", nullable = false)
    val email: String,

    @field:Column(columnDefinition = "VARCHAR(255)", nullable = false)
    val password: String,

    @field:Column(columnDefinition = "VARCHAR(11)", nullable = false)
    val phoneNumber: String,

    @field:Column(columnDefinition = "VARCHAR(5)", nullable = false)
    val name: String,

    @field:Column(columnDefinition = "VARCHAR(255)", nullable = false)
    val position: String,

    @field:Enumerated(EnumType.STRING)
    @field:Column(columnDefinition = "VARCHAR(10)", nullable = false)
    val status: Status

) : BaseUUIDEntity() {
}