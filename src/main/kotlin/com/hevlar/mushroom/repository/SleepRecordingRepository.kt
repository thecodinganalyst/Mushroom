package com.hevlar.mushroom.repository

import com.hevlar.mushroom.model.SleepRecording
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SleepRecordingRepository: JpaRepository<SleepRecording, Long> {
    fun findByChildId(childId: Long): List<SleepRecording>
    fun findTopByChildIdOrderByDateTimeDesc(childId: Long): SleepRecording?
}
