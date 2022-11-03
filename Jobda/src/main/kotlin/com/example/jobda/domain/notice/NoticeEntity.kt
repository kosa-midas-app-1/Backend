package com.example.jobda.domain.notice

import com.example.jobda.common.BaseUUIDEntity
import com.example.jobda.domain.company.CompanyEntity
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

/**
 *
 * NoticeEntity
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
@EntityListeners(AuditingEntityListener::class)
@Entity
@Table(name = "tbl_notice")
class NoticeEntity(

    override val id: UUID,

    @field:Column(columnDefinition = "TEXT", nullable = false)
    val content: String,

    @field:CreatedDate
    @field:Column(columnDefinition = "DATETIME", nullable = false)
    val createdAt: LocalDateTime,

    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "company_id",columnDefinition = "VARCHAR(16)", nullable = false)
    val companyEntity: CompanyEntity?

    ) : BaseUUIDEntity() {
}