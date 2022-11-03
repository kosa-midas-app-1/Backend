package com.example.jobda.infrastructure.error.exception

import com.example.jobda.infrastructure.error.GlobalErrorCode
import com.example.jobda.infrastructure.error.custom.CustomException

/**
 *
 * InternalServerErrorException
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
object InternalServerErrorException : CustomException(
    GlobalErrorCode.INTERNAL_SERVER_ERROR
) {
}