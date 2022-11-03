package com.example.jobda.domain.workHomeRequest

import com.example.jobda.domain.company.CompanyEntity
import com.example.jobda.domain.staff.StaffEntity
import java.io.Serializable
import java.time.LocalDate
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.MapsId
import javax.persistence.Table

/**
 *
 * WorkHomeRequestEntity
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
@Entity
@Table(name = "tbl_work_home_request")
class WorkHomeRequestEntity(

    @field:EmbeddedId
    val id: WorkHomeRequestId,

    @field:MapsId("staffId")
    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "staff_id", columnDefinition = "BINARY(16)", nullable = false)
    val staffEntity: StaffEntity?,

    @field:MapsId("companyId")
    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "company_id", columnDefinition = "BINARY(16)", nullable = false)
    val companyEntity: CompanyEntity?,

    @field:Column(columnDefinition = "VARCHAR(255)", nullable = false)
    val reason: String

) {
}

@Embeddable
data class WorkHomeRequestId(

    @field:Column(columnDefinition = "DATE", nullable = false)
    val date: LocalDate,

    @field:Column(nullable = false)
    val staffId: UUID,

    @field:Column(nullable = false)
    val companyId: UUID

) : Serializable