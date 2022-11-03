package com.example.jobda.infrastructure.security.jwt

import com.example.jobda.domain.auth.RefreshTokenEntity
import com.example.jobda.domain.auth.controller.dto.response.TokenResponse
import com.example.jobda.domain.auth.repository.RefreshTokenRepository
import com.example.jobda.domain.auth.type.Authority
import com.example.jobda.infrastructure.security.properties.SecurityProperties
import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID

/**
 *
 * JwtProvider
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Component
class JwtProvider(
    private val securityProperties: SecurityProperties,
    private val refreshTokenRepository: RefreshTokenRepository
) {

    companion object {
        const val ACCESS = "access"
        const val REFRESH = "refresh"
        const val AUTHORITY = "authority"
    }

    fun receiveToken(userId: UUID, authority: Authority) = TokenResponse(
        accessToken = generateAccessToken(userId, authority),
        expiredAt = LocalDateTime.now().plusSeconds(securityProperties.accessExp.toLong()),
        refreshToken = generateRefreshToken(userId, authority),
        authority = authority
    )

    private fun generateAccessToken(userId: UUID, authority: Authority) =
        Jwts.builder()
            .signWith(SignatureAlgorithm.HS512, securityProperties.secretKey)
            .setHeaderParam(Header.JWT_TYPE, ACCESS)
            .setId(userId.toString())
            .claim(AUTHORITY, authority.name)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + securityProperties.accessExp))
            .compact()

    private fun generateRefreshToken(userId: UUID, authority: Authority): String {
        val token = Jwts.builder()
            .signWith(SignatureAlgorithm.HS512, securityProperties.secretKey)
            .setHeaderParam(Header.JWT_TYPE, REFRESH)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + securityProperties.refreshExp))
            .compact()

        val refreshToken = RefreshTokenEntity(
            token = token,
            userId = userId,
            authority = authority,
            expirationTime = securityProperties.refreshExp / 1000
        )
        refreshTokenRepository.save(refreshToken)

        return token
    }
}