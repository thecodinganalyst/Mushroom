package com.hevlar.mushroom

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MushroomApplication

fun main(args: Array<String>) {
    runApplication<MushroomApplication>(*args)
}
