package com.example.jobda.domain.manager.error

import com.example.jobda.infrastructure.error.custom.CustomErrorProperty

/**
 *
 * StaffErrorCode
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
enum class StaffErrorCode(
    private val status: Int,
    private val message: String
) : CustomErrorProperty {

    STAFF_NOT_FOUND(404, "Staff Not Found")
    ;

    override fun status(): Int = status
    override fun message(): String = message
}