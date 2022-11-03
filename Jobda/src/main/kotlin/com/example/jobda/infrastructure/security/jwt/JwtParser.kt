package com.example.jobda.infrastructure.security.jwt

import com.example.jobda.domain.auth.type.Authority
import com.example.jobda.infrastructure.error.exception.InternalServerErrorException
import com.example.jobda.infrastructure.security.exception.ExpiredTokenException
import com.example.jobda.infrastructure.security.exception.InvalidRoleException
import com.example.jobda.infrastructure.security.exception.InvalidTokenException
import com.example.jobda.infrastructure.security.exception.UnexpectedTokenException
import com.example.jobda.infrastructure.security.principle.CustomManagerDetailsService
import com.example.jobda.infrastructure.security.principle.CustomStaffDetailsService
import com.example.jobda.infrastructure.security.properties.SecurityProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Header
import io.jsonwebtoken.InvalidClaimException
import io.jsonwebtoken.Jws
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

/**
 *
 * JwtParser
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.2.0
 **/
@Component
class JwtParser(
    private val securityProperties: SecurityProperties,
    private val staffDetailsService: CustomStaffDetailsService,
    private val managerDetailsService: CustomManagerDetailsService
) {

    fun getAuthentication(token: String): Authentication {
        val claims = getClaims(token)

        if (claims.header[Header.JWT_TYPE] != JwtProvider.ACCESS) {
            throw InvalidTokenException
        }

        val userDetails = getDetails(claims.body)

        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun getClaims(token: String): Jws<Claims> {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(securityProperties.key).build()
                .parseClaimsJws(token)
        } catch (e: Exception) {
            when (e) {
                is InvalidClaimException -> throw InvalidTokenException
                is ExpiredJwtException -> throw ExpiredTokenException
                is JwtException -> throw UnexpectedTokenException
                else -> throw InternalServerErrorException
            }
        }
    }

    private fun getDetails(body: Claims): UserDetails {

        return when (body.get("authority", Authority::class.java)) {
            Authority.STAFF -> staffDetailsService.loadUserByUsername(body.id)
            Authority.MANAGER -> managerDetailsService.loadUserByUsername(body.id)
            else -> throw InvalidRoleException
        }
    }
}