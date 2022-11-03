package com.example.jobda.domain.notice.repository

import com.example.jobda.domain.notice.NoticeEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

/**
 *
 * NoticeRepository
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
@Repository
interface NoticeRepository : CrudRepository<NoticeEntity, UUID> {
    fun findByCompanyEntityId(companyId: UUID): List<NoticeEntity>
    fun findTopByCompanyEntityIdOrderByCreatedAtDesc(companyId: UUID): NoticeEntity?
}