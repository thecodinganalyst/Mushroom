package com.hevlar.mushroom.repository

import com.hevlar.mushroom.model.MilkRecording
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MilkRecordingRepository: JpaRepository<MilkRecording, Long>{
    fun findByChildId(id: Long): List<MilkRecording>
}
