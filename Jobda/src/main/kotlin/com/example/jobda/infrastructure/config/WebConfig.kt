package com.example.jobda.infrastructure.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


/**
 *
 * WebConfig
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedMethods("*")
            .allowedOrigins("*")
    }
}