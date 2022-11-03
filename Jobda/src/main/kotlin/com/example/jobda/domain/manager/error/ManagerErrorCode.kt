package com.example.jobda.domain.manager.error

import com.example.jobda.infrastructure.error.custom.CustomErrorProperty

/**
 *
 * ManagerErrorCode
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
enum class ManagerErrorCode(
    private val status: Int,
    private val message: String
) : CustomErrorProperty {

    MANAGER_NOT_FOUND(404, "Manager Not Found")
    ;

    override fun status(): Int = status
    override fun message(): String = message
}