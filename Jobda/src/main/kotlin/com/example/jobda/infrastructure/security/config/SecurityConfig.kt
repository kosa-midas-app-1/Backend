package com.example.jobda.infrastructure.security.config

import com.example.jobda.infrastructure.config.FilterConfig
import com.example.jobda.infrastructure.security.jwt.JwtParser
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

/**
 *
 * SecurityConfiguration
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
@Configuration
class SecurityConfig(
    private val jwtParser: JwtParser,
    private val objectMapper: ObjectMapper
) {

    @Bean
    protected fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf().disable()
            .cors().and()

        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http
            .authorizeRequests()

        http
            .apply(FilterConfig(jwtParser, objectMapper))

        return http.build()
    }
}