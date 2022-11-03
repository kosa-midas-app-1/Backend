package com.example.jobda.infrastructure.error

import com.example.jobda.infrastructure.error.response.BindErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.validation.BindException
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 *
 * GlobalErrorHandler
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
@RestControllerAdvice
class GlobalErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException::class)
    protected fun handleBindException(e: BindException): BindErrorResponse? {

        val errorMap = HashMap<String, String?>()

        for (error: FieldError in e.fieldErrors) {
            errorMap[error.field] = error.defaultMessage
        }

        return BindErrorResponse(
            status = GlobalErrorCode.BAD_REQUEST.status(),
            fieldError = listOf(errorMap)
        )
    }

}