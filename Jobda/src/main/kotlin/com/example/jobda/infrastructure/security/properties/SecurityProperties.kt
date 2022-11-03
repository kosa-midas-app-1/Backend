package com.example.jobda.infrastructure.security.properties

import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.security.Key
import java.util.Base64

/**
 *
 * SecurityProperties
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 2.0.0
 **/
@ConfigurationProperties(prefix = "jwt")
@ConstructorBinding
class SecurityProperties(
    secretKey: String,
    accessExp: Int,
    refreshExp: Int
) {
    val key: Key by lazy {
        val secretKey: String = Base64.getEncoder().encodeToString(secretKey.toByteArray())
        Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))
    }
    val accessExp: Int = accessExp * 1000
    val refreshExp: Int = refreshExp * 1000
}