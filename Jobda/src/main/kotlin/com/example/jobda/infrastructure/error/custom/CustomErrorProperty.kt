package com.example.jobda.infrastructure.error.custom

/**
 *
 * ErrorProperty
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
interface CustomErrorProperty {

    fun status(): Int

    fun message(): String

}