package com.hevlar.mushroom.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "DiaperChange")
class DiaperChangeRecording(
    @Id
    @GeneratedValue
    val id: Long,
    override val dateTime: LocalDateTime,
    val diaperStatus: DiaperStatus,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    val child: Child
) : Recording {
    override fun getText(): String {
        return diaperStatus.name
    }
}
