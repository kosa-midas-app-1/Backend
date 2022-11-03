package com.example.jobda.domain.staff

import com.example.jobda.common.BaseUUIDEntity
import com.example.jobda.domain.company.CompanyEntity
import com.example.jobda.domain.staff.type.Status
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

/**
 *
 * StaffEntity
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.4.0
 **/
@Entity
@Table(name = "tbl_staff")
class StaffEntity(

    override val id: UUID = UUID(0, 0),

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
    val status: Status,

    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "company_id",columnDefinition = "BINARY(16)", nullable = false)
    val companyEntity: CompanyEntity?

) : BaseUUIDEntity() {

    fun updateStaffInfo(email: String, phoneNumber: String, position: String): StaffEntity {
        return StaffEntity(
            id = id,
            email = email,
            password = password,
            phoneNumber = phoneNumber,
            name = name,
            position = position,
            status = status,
            companyEntity = companyEntity
        )
    }

    fun updateStaffLeaveEarly(): StaffEntity {
        return StaffEntity(
            id = id,
            email = email,
            password = password,
            phoneNumber = phoneNumber,
            name = name,
            position = position,
            status = Status.LEAVE_EARLY,
            companyEntity = companyEntity
        )
    }

    fun approveJoinRequest(companyEntity: CompanyEntity): StaffEntity {
        return StaffEntity(
            id = id,
            email = email,
            password = password,
            phoneNumber = phoneNumber,
            name = name,
            position = position,
            status = status,
            companyEntity = companyEntity
        )
    }

    fun updateStatus(status: Status): StaffEntity {
        return StaffEntity(
            id = id,
            email = email,
            password = password,
            phoneNumber = phoneNumber,
            name = name,
            position = position,
            status = status,
            companyEntity = companyEntity
        )
    }
}