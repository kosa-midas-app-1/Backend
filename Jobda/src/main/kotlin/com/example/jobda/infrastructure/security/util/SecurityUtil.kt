package com.example.jobda.infrastructure.security.util

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.UUID

/**
 *
 * SecurityUtil
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Component
class SecurityUtil {

    fun getCurrentUserId(): UUID {
        return UUID.fromString(SecurityContextHolder.getContext().authentication.name)
    }
}