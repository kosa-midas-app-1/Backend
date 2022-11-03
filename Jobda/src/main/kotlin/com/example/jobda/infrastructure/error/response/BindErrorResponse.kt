package com.example.jobda.infrastructure.error.response

/**
 *
 * BindErrorResponse
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
data class BindErrorResponse(
    val status: Int,
    val fieldError: List<Map<String, String?>>
)