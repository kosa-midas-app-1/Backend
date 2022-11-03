package com.example.jobda.domain.auth.error

import com.example.jobda.infrastructure.error.custom.CustomErrorProperty

/**
 *
 * AuthErrorCode
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
enum class AuthErrorCode(
    private val status: Int,
    private val message: String
) : CustomErrorProperty {

    INVALID_PASSWORD_EXCEPTION(400, "Invalid Password"),
    AUTH_CODE_NOT_FOUND(404, "Auth Code Not Found"),
    EMAIL_NOT_FOUND(400, "Email Not Found")
    ;

    override fun status(): Int = status
    override fun message(): String = message
}