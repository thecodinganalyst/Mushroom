package com.hevlar.mushroom.service

import com.hevlar.mushroom.model.MilkRecording
import com.hevlar.mushroom.repository.MilkRecordingRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MilkRecordingService(val milkRecordingRepository: MilkRecordingRepository) {
    fun addMilkRecording(milkRecording: MilkRecording): MilkRecording {
        return milkRecordingRepository.save(milkRecording)
    }

    fun editMilkRecording(milkRecording: MilkRecording): MilkRecording {
        return milkRecordingRepository.save(milkRecording)
    }

    fun listMilkRecordings(): List<MilkRecording> {
        return milkRecordingRepository.findAll()
    }

    fun getMilkRecording(id: Long): MilkRecording? {
        return milkRecordingRepository.findByIdOrNull(id);
    }

    fun deleteMilkRecording(id: Long) {
        milkRecordingRepository.deleteById(id)
    }
}
