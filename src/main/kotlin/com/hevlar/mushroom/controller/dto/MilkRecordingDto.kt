package com.hevlar.mushroom.controller.dto

import com.hevlar.mushroom.model.MilkType
import java.time.LocalDateTime

data class MilkRecordingDto(val dateTime: LocalDateTime, val until: LocalDateTime?, val type: MilkType, val amount: Int?)
