package com.example.jobda.domain.auth.exception

import com.example.jobda.domain.auth.error.AuthErrorCode
import com.example.jobda.infrastructure.error.custom.CustomException

/**
 *
 * StaffAlreadyExists
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
object EmailAlreadyExistsException : CustomException(
    AuthErrorCode.EMAIL_ALREADY_EXISTS
)