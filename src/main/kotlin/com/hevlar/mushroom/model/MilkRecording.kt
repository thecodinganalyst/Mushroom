package com.hevlar.mushroom.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "MilkRecording")
class MilkRecording(
    @Id
    @GeneratedValue
    val id: Long,
    override var dateTime: LocalDateTime,
    override var until: LocalDateTime?,
    var type: MilkType,
    var amount: Int?,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    var child: Child
    ) : DurationRecording {

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

}
