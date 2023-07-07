package com.hevlar.mushroom

import com.hevlar.mushroom.config.MushroomConfigurations
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(MushroomConfigurations::class)
class MushroomApplication

fun main(args: Array<String>) {
    runApplication<MushroomApplication>(*args)
}
