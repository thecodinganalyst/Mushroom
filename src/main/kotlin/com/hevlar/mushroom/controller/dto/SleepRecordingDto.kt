package com.hevlar.mushroom.controller.dto

import java.time.LocalDateTime

data class SleepRecordingDto(val dateTime: LocalDateTime, val until: LocalDateTime?)
