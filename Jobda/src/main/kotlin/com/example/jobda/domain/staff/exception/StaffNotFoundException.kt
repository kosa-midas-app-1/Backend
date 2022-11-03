package com.example.jobda.domain.manager.exception

import com.example.jobda.domain.manager.error.StaffErrorCode
import com.example.jobda.infrastructure.error.custom.CustomException

/**
 *
 * ManagerNotFoundException
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
object StaffNotFoundException : CustomException(
    StaffErrorCode.STAFF_NOT_FOUND
)