package com.example.jobda.domain.company

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.MapsId
import javax.persistence.OneToOne
import javax.persistence.Table

/**
 *
 * RequiredWorkTimeEntity
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.1
 **/
@Entity
@Table(name = "tbl_requried_work_time")
class RequiredWorkTimeEntity(

    @field:Id
    val companyId: UUID,

    @field:MapsId
    @field:OneToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "company_id", columnDefinition = "BINARY(16)", nullable = false)
    val companyEntity: CompanyEntity?,

    @field:Column(columnDefinition = "DATETIME", nullable = false)
    val startAt: LocalDateTime,

    @field:Column(columnDefinition = "DATETIME", nullable = false)
    val endAt: LocalDateTime

) {
}