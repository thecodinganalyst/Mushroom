package com.hevlar.mushroom.model

import java.time.Duration
import java.time.LocalDateTime

interface DurationRecording: Recording {
    val until: LocalDateTime?

    fun getDurationText(): String {
        if(until == null) return ""
        return Duration.between(dateTime, until).let {
            val hour = it.toHoursPart()
            val min = it.toMinutesPart()
            when {
                hour > 0 && min > 0 -> " ${hour}h ${min}m"
                hour > 0 -> " ${hour}h"
                min > 0 -> " ${min}m"
                else -> " < 1m"
            }
        }
    }
}
