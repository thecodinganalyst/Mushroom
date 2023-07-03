package com.hevlar.mushroom.model

import jakarta.persistence.*
import java.lang.StringBuilder
import java.time.Duration
import java.time.LocalDateTime

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "MilkRecording")
class MilkRecording(
    @Id
    @GeneratedValue
    val id: Long,
    override val dateTime: LocalDateTime,
    val until: LocalDateTime?,
    val type: MilkType,
    val amount: Int?,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    val child: Child
    ) : Recording {

    override fun getText(): String {
        return when(type){
            MilkType.BreastLeft -> "Left Breast ${getDurationText()}".trim()
            MilkType.BreastRight -> "Right Breast ${getDurationText()}".trim()
            MilkType.BottleBreast -> "Bottled Breast-milk${getBottleVolumeText()} ${getDurationText()}".trim()
            MilkType.BottleFormula -> "Formula milk${getBottleVolumeText()} ${getDurationText()}".trim()
        }
    }

    private fun getBottleVolumeText(): String {
        if(amount == null) return ""
        return "$amount ml"
    }

    private fun getDurationText(): String {
        if(until == null) return "";
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
