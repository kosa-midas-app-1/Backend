package com.example.jobda.infrastructure.security.exception

import com.example.jobda.infrastructure.error.custom.CustomErrorCode
import com.example.jobda.infrastructure.error.custom.CustomException

/**
 *
 * ExpiredTokenException
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
object ExpiredTokenException : CustomException(
    CustomErrorCode.EXPIRED_TOKEN
)