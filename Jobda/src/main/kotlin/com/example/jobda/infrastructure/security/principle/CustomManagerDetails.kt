package com.example.jobda.infrastructure.security.principle

import com.example.jobda.domain.auth.type.Authority
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

/**
 *
 * CustomManagerDetails
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
class CustomManagerDetails(
    private val managerId: UUID
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(Authority.MANAGER.name))
    }

    override fun getPassword(): String? = null

    override fun getUsername(): String = managerId.toString()

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}