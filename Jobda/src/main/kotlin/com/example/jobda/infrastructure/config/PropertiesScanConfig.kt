package com.example.jobda.infrastructure.config

import com.example.jobda.infrastructure.security.properties.SecurityProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

/**
 *
 * PropertiesScanConfiguration
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
@ConfigurationPropertiesScan(
    basePackageClasses = [
        SecurityProperties::class
    ]
)
@Configuration
class PropertiesScanConfig {
}