package com.example.jobda.infrastructure.security.util

import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

/**
 *
 * TimeUtil
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Component
class TimeUtil {

    fun betweenForMinutes(startAt: LocalDateTime, endAt: LocalDateTime): Long {
        return ChronoUnit.HOURS.between(startAt, endAt) * 60 + ChronoUnit.MINUTES.between(startAt, endAt)
    }

    fun betweenHour(startAt: LocalDateTime, endAt: LocalDateTime): Long {
        return ChronoUnit.HOURS.between(startAt, endAt)
    }
}