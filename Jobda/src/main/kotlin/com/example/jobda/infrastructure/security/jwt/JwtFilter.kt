package com.example.jobda.infrastructure.security.jwt

import com.example.jobda.infrastructure.security.exception.InvalidTokenException
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *
 * JwtFilter
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 2.0.0
 **/
class JwtFilter(
    private val jwtParser: JwtParser
) : OncePerRequestFilter() {

    companion object {
        private const val PREFIX = "Bearer "
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = resolvedToken(request)

        if(token != null) {
            SecurityContextHolder.getContext().authentication = jwtParser.getAuthentication(token)
        } else {
            SecurityContextHolder.clearContext()
        }

        filterChain.doFilter(request, response)
    }

    private fun resolvedToken(request: HttpServletRequest): String? {
        val bearerToken: String? = request.getHeader(HttpHeaders.AUTHORIZATION)

        bearerToken?.let {
            if (bearerToken.startsWith(PREFIX)) {
                return bearerToken.substring(PREFIX.length)
            }
        }

        return null
    }
}