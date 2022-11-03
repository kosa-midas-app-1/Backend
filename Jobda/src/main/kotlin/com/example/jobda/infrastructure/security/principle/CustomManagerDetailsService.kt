package com.example.jobda.infrastructure.security.principle

import com.example.jobda.domain.manager.repository.ManagerRepository
import com.example.jobda.infrastructure.security.exception.InvalidTokenException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.UUID

/**
 *
 * CustomManagerDetailsService
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
@Component
class CustomManagerDetailsService(
    private val managerRepository: ManagerRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val manager = managerRepository.findByIdOrNull(UUID.fromString(username)) ?: throw InvalidTokenException

        return CustomManagerDetails(manager.id)
    }
}