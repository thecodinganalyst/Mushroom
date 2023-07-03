package com.hevlar.mushroom.repository

import com.hevlar.mushroom.model.DiaperChangeRecording
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DiaperChangeRepository: JpaRepository<DiaperChangeRecording, Long> {
    fun findByChildId(childId: Long): List<DiaperChangeRecording>
}
