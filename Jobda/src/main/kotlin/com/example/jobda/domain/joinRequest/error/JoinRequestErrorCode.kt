package com.example.jobda.domain.company.error

import com.example.jobda.infrastructure.error.custom.CustomErrorProperty

/**
 *
 * JoinRequestErrorCode
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
enum class JoinRequestErrorCode(
    private val status: Int,
    private val message: String
) : CustomErrorProperty {

    JOIN_REQUEST_ALREADY_EXISTS(409, "Join Request Already Exists")
    ;

    override fun status(): Int = status
    override fun message(): String = message
}