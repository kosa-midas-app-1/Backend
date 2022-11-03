package com.example.jobda.domain.manager.exception

import com.example.jobda.domain.manager.error.ManagerErrorCode
import com.example.jobda.infrastructure.error.custom.CustomException

/**
 *
 * ManagerNotFoundException
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
object ManagerNotFoundException : CustomException(
    ManagerErrorCode.MANAGER_NOT_FOUND
)