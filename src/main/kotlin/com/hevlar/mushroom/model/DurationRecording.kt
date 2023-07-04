package com.hevlar.mushroom.model

import com.hevlar.mushroom.model.validation.ValidDuration
import java.time.Duration
import java.time.LocalDateTime

@ValidDuration
interface DurationRecording: Recording {
    var until: LocalDateTime?

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
