package com.hevlar.mushroom.model

import java.time.LocalDateTime

interface Recording {
    var dateTime: LocalDateTime

    fun getText(): String
}
