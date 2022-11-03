package com.example.jobda.infrastructure.error

import com.example.jobda.infrastructure.error.custom.CustomErrorProperty
import com.example.jobda.infrastructure.error.custom.CustomException
import com.example.jobda.infrastructure.error.response.ErrorResponse
import com.example.jobda.infrastructure.error.exception.InternalServerErrorException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *
 * GlobalExceptionFilter
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
class GlobalExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: Exception) {
            when (e) {
                is CustomException -> errorToJson((e.cause as CustomException).errorProperty, response)
                else -> {
                    errorToJson(InternalServerErrorException.errorProperty, response)
                    e.printStackTrace()
                }
            }
        }
    }

    private fun errorToJson(errorProperty: CustomErrorProperty, response: HttpServletResponse) {
        response.status = errorProperty.status()
        response.characterEncoding = "UTF-8"
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(objectMapper.writeValueAsString(
            ErrorResponse(
                status = errorProperty.status(),
                message = errorProperty.message())
        ))
    }
}