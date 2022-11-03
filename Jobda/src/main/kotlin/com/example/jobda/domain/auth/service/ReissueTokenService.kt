package com.example.jobda.domain.auth.service

import com.example.jobda.domain.auth.controller.dto.response.TokenResponse
import com.example.jobda.domain.auth.exception.RefreshTokenNotFoundException
import com.example.jobda.domain.auth.repository.RefreshTokenRepository
import com.example.jobda.infrastructure.security.jwt.JwtProvider
import org.springframework.stereotype.Service

/**
 *
 * ReissueTokenService
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Service
class ReissueTokenService(
    private val jwtProvider: JwtProvider,
    private val refreshTokenRepository: RefreshTokenRepository
) {

    fun execute(refreshToken: String): TokenResponse {
        val refreshTokenEntity = refreshTokenRepository.findByToken(refreshToken) ?: throw RefreshTokenNotFoundException

        return jwtProvider.receiveToken(refreshTokenEntity.userId, refreshTokenEntity.authority)
    }
}