package com.hevlar.mushroom.model

import java.time.LocalDateTime

interface Recording {
    val dateTime: LocalDateTime

    fun getText(): String
}
