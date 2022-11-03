package com.example.jobda.domain.staff

import com.example.jobda.domain.company.CompanyEntity
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
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
 * WorkRecordEntity
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.1.0
 **/
@Entity
@Table(name = "tbl_work_record")
class WorkRecordEntity(

    @field:EmbeddedId
    val id: WorkRecordId,

    @field:MapsId("staffId")
    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "staff_id", columnDefinition = "BINARY(16)", nullable = false)
    val staffEntity: StaffEntity?,

    @field:MapsId("companyId")
    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "company_id", columnDefinition = "BINARY(16)", nullable = false)
    val companyEntity: CompanyEntity?,

    @field:Column(columnDefinition = "DATETIME", nullable = false)
    val startAt: LocalDateTime,

    @field:Column(columnDefinition = "DATETIME", nullable = false)
    val endAt: LocalDateTime?,

    @field:Column(columnDefinition = "TINYINT(1)", nullable = false)
    val isWorkHome: Boolean

) {
}

@Embeddable
data class WorkRecordId(

    @field:Column(columnDefinition = "DATE", nullable = false)
    val date: LocalDate,

    @field:Column(nullable = false)
    val staffId: UUID,

    @field:Column(nullable = false)
    val companyId: UUID

) : Serializable