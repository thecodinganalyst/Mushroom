package com.hevlar.mushroom.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "SleepRecording")
class SleepRecording(
    @Id
    @GeneratedValue
    val id: Long,
    override var dateTime: LocalDateTime,
    override var until: LocalDateTime?,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    var child: Child
    ) : DurationRecording {
    override fun getText(): String {
        return if (until != null){
            "sleep for ${getDurationText()}"
        }else{
            "fell asleep"
        }
    }

}
