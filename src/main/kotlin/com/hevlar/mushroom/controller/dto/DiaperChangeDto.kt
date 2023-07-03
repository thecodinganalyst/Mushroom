package com.hevlar.mushroom.controller.dto

import com.hevlar.mushroom.model.DiaperChangeRecording
import com.hevlar.mushroom.model.DiaperStatus
import java.time.LocalDateTime

data class DiaperChangeDto(val dateTime: LocalDateTime, val diaperStatus: DiaperStatus)

fun DiaperChangeRecording.toDto() = DiaperChangeDto(dateTime, diaperStatus)
