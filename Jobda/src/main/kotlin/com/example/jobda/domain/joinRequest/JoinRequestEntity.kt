package com.example.jobda.domain.joinRequest

import com.example.jobda.domain.company.CompanyEntity
import com.example.jobda.domain.staff.StaffEntity
import java.io.Serializable
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
 * JoinRequestEntity
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
@Entity
@Table(name = "tbl_join_request")
class JoinRequestEntity(

    @field:EmbeddedId
    val id: JoinRequestId,

    @MapsId("staffId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", columnDefinition = "BINARY(16)", nullable = false)
    val staffEntity: StaffEntity?,

    @MapsId("companyId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", columnDefinition = "BINARY(16)", nullable = false)
    val companyEntity: CompanyEntity?

) {
}

@Embeddable
data class JoinRequestId(

    @field:Column(nullable = false)
    val staffId: UUID,

    @field:Column(nullable = false)
    val companyId: UUID
) : Serializable