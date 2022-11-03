package com.example.jobda.infrastructure.error.exception

import com.example.jobda.infrastructure.error.custom.CustomErrorCode
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
    CustomErrorCode.INTERNAL_SERVER_ERROR
) {
}