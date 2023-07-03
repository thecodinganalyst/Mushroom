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
    override var dateTime: LocalDateTime,
    var diaperStatus: DiaperStatus,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    var child: Child?
) : Recording {
    override fun getText(): String {
        return diaperStatus.name
    }
}
