package com.example.jobda.domain.auth.controller.dto.response

import com.example.jobda.domain.auth.type.Authority
import java.time.LocalDateTime

/**
 *
 * TokenResponse
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
data class TokenResponse(
    val accessToken: String,
    val expiredAt: LocalDateTime,
    val refreshToken: String,
    val authority: Authority?
)
