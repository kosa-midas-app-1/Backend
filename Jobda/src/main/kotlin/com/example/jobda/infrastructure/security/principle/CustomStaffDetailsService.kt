package com.example.jobda.infrastructure.security.principle

import com.example.jobda.domain.staff.repository.StaffRepository
import com.example.jobda.infrastructure.security.exception.InvalidTokenException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.UUID

/**
 *
 * CustomStaffDetailsService
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
@Component
class CustomStaffDetailsService(
    private val staffRepository: StaffRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val staff = staffRepository.findByIdOrNull(UUID.fromString(username)) ?: throw InvalidTokenException

        return CustomStaffDetails(staff.id)
    }
}