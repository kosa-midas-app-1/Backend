package com.example.jobda.domain.auth.controller.dto.request

/**
 *
 * SignInRequest
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
data class SignInRequest(
    val email: String,
    val password: String
)