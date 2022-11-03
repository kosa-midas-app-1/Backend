package com.example.jobda.infrastructure.security.config

import com.example.jobda.domain.auth.type.Authority
import com.example.jobda.infrastructure.config.FilterConfig
import com.example.jobda.infrastructure.security.jwt.JwtParser
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

/**
 *
 * SecurityConfiguration
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 5.0.0
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

            //auth
            .antMatchers("/auth/**").permitAll()

            //managers
            .antMatchers(HttpMethod.POST, "/managers").hasAuthority(Authority.MANAGER.name)
            .antMatchers(HttpMethod.GET, "/managers").hasAuthority(Authority.MANAGER.name)

            //companies
            .antMatchers(HttpMethod.PATCH, "/companies/work-time").hasAuthority(Authority.MANAGER.name)
            .antMatchers(HttpMethod.PATCH, "/companies").hasAuthority(Authority.MANAGER.name)
            .antMatchers(HttpMethod.GET, "/companies/search").permitAll()
            .antMatchers(HttpMethod.GET, "/companies").hasAnyAuthority(Authority.MANAGER.name, Authority.STAFF.name)

            //staff
            .antMatchers(HttpMethod.GET, "/staff").hasAuthority(Authority.MANAGER.name)
            .antMatchers(HttpMethod.GET, "/staff/{staff-id}").hasAuthority(Authority.MANAGER.name)
            .antMatchers(HttpMethod.PATCH, "/staff/{staff-id}").hasAuthority(Authority.MANAGER.name)
            .antMatchers(HttpMethod.PATCH, "/staff/leave-early{staff-id}").hasAuthority(Authority.MANAGER.name)
            .antMatchers(HttpMethod.GET, "/staff/average").hasAuthority(Authority.MANAGER.name)
            .antMatchers(HttpMethod.GET, "/staff/timeline").hasAuthority(Authority.MANAGER.name)
            .antMatchers(HttpMethod.GET,"/staff/today").hasAuthority(Authority.MANAGER.name)
            .antMatchers(HttpMethod.GET, "/staff/work-time").hasAuthority(Authority.STAFF.name)
            .antMatchers(HttpMethod.GET, "/staff/work-time/week").hasAuthority(Authority.STAFF.name)
            .antMatchers(HttpMethod.GET, "/staff/myself").hasAuthority(Authority.STAFF.name)
            .antMatchers(HttpMethod.PATCH, "/staff/myself").hasAuthority(Authority.STAFF.name)

            //join-requests
            .antMatchers(HttpMethod.GET, "/join-requests").hasAuthority(Authority.MANAGER.name)
            .antMatchers(HttpMethod.PATCH, "/join-requests/{staff-id}").hasAuthority(Authority.MANAGER.name)
            .antMatchers(HttpMethod.DELETE, "/join-requests/{staff-id}").hasAuthority(Authority.MANAGER.name)
            .antMatchers(HttpMethod.POST, "/join-requests/{company-id}").hasAuthority(Authority.STAFF.name)


        http
            .apply(FilterConfig(jwtParser, objectMapper))

        return http.build()
    }

    @Bean
    protected fun passwordEncoder() = BCryptPasswordEncoder()
}