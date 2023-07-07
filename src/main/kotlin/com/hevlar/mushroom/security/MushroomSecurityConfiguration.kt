package com.hevlar.mushroom.security

import com.hevlar.mushroom.config.MushroomConfigurations
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource

@Configuration
@EnableWebSecurity
class MushroomSecurityConfiguration(val mushroomConfigurations: MushroomConfigurations) {

    private val logger = LoggerFactory.getLogger(MushroomSecurityConfiguration::class.java)

    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity
            .cors { cors -> cors.configurationSource(getCorsConfiguration()) }
            .csrf { csrf -> csrf.disable() }
        return httpSecurity.build()
    }

    @Bean
    fun getCorsConfiguration(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = mushroomConfigurations.allowedOrigins
        configuration.applyPermitDefaultValues()
        logger.info("CORS allowed origins - ${mushroomConfigurations.allowedOrigins}")
        return CorsConfigurationSource { configuration }
    }

}
