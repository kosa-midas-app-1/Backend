package com.example.jobda.infrastructure.error.custom

/**
 *
 * ErrorCode
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.1
 **/
enum class CustomErrorCode(
    private val status: Int,
    private val message: String
) : CustomErrorProperty {

    BAD_REQUEST(400, "Bad Request"),

    INVALID_TOKEN(401, "Invalid Token"),
    EXPIRED_TOKEN(401, "Expired Token"),
    UNEXPECTED_TOKEN(401, "Unexpected Token"),
    INVALID_ROLE(401, "Invalid Role"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    override fun status(): Int = status
    override fun message(): String = message
}