package com.hevlar.mushroom.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "mushroom")
data class MushroomConfigurations (
    val allowedOrigins: List<String> = listOf()
)
