package com.example.jobda.infrastructure.security.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.util.Base64

/**
 *
 * SecurityProperties
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
@ConfigurationProperties(prefix = "jwt")
@ConstructorBinding
class SecurityProperties(
    secretKey: String,
    accessExp: Int,
    refreshExp: Int
) {
    val secretKey: String = Base64.getEncoder().encodeToString(secretKey.toByteArray())
    val accessExp: Int = accessExp * 1000
    val refreshExp: Int = refreshExp * 1000
}