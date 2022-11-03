package com.example.jobda.domain.company.exception

import com.example.jobda.domain.company.error.JoinRequestErrorCode
import com.example.jobda.infrastructure.error.custom.CustomException

/**
 *
 * CompanyNotFoundException
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
object JoinRequestAlreadyExistsException : CustomException(
    JoinRequestErrorCode.JOIN_REQUEST_ALREADY_EXISTS
)