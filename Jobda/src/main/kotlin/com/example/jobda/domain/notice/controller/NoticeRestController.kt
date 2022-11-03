package com.example.jobda.domain.notice.controller

import com.example.jobda.domain.manager.exception.ManagerNotFoundException
import com.example.jobda.domain.manager.exception.StaffNotFoundException
import com.example.jobda.domain.manager.repository.ManagerRepository
import com.example.jobda.domain.notice.NoticeEntity
import com.example.jobda.domain.notice.repository.NoticeRepository
import com.example.jobda.domain.staff.repository.StaffRepository
import com.example.jobda.infrastructure.security.util.SecurityUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import javax.validation.Valid

/**
 *
 * NoticeWebController
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
//TODO 시간이 부족한 관계로..
@RequestMapping("/first")
@RestController
class NoticeRestController(
    private val noticeRepository: NoticeRepository,
    private val securityUtil: SecurityUtil,
    private val managerRepository: ManagerRepository,
    private val staffRepository: StaffRepository
) {

    @PostMapping
    fun postNotice(@RequestBody @Valid request: NoticeRequest) {
        val id = securityUtil.getCurrentUserId()
        val manager = managerRepository.findByIdOrNull(id) ?: throw ManagerNotFoundException
        noticeRepository.save(
            NoticeEntity(
               content = request.content,
                createdAt = LocalDateTime.now(),
                companyEntity = manager.companyEntity!!
            )
        )
    }

    @PatchMapping
    fun updateNotice(@RequestBody @Valid request: NoticeRequest) {
        val id = securityUtil.getCurrentUserId()
        val manager = managerRepository.findByIdOrNull(id) ?: throw ManagerNotFoundException
        noticeRepository.save(
            NoticeEntity(
                content = request.content,
                createdAt = LocalDateTime.now(),
                companyEntity = manager.companyEntity!!
            )
        )
    }

    @GetMapping
    fun getNotices(): NoticesResponse {
        val userId = securityUtil.getCurrentUserId()
        val companyEntity = managerRepository.findByIdOrNull(userId).let {
            if (it == null) {
                staffRepository.findByIdOrNull(userId)!!.companyEntity!!
            } else {
                it.companyEntity!!
            }
        }

        val notices = noticeRepository.findByCompanyEntityId(companyEntity.id).map {
            NoticeResponse(
                content = it.content,
                createdAt = it.createdAt
            )
        }

        return NoticesResponse(notices)
    }

    @GetMapping("/first")
    fun getFirstNotice(): NoticeResponse {
        val userId = securityUtil.getCurrentUserId()
        val staffEntity = staffRepository.findByIdOrNull(userId) ?: throw StaffNotFoundException
        val noticeEntity = noticeRepository.findTopByCompanyEntityIdOrderByCreatedAtDesc(staffEntity.companyEntity!!.id)!!

        return NoticeResponse(
            content = noticeEntity.content,
            createdAt = noticeEntity.createdAt
        )
    }
}

data class NoticeRequest(
    val content: String
)

data class NoticeResponse(
    val content: String,
    val createdAt: LocalDateTime
)

data class NoticesResponse(
    val notices: List<NoticeResponse>
)