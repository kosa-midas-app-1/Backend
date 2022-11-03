package com.example.jobda.infrastructure.error.custom

import com.example.jobda.infrastructure.error.custom.CustomErrorProperty
import java.lang.RuntimeException

/**
 *
 * CustomException
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
abstract class CustomException(
    val errorProperty: CustomErrorProperty
) : RuntimeException() {
    override fun fillInStackTrace() = this
}