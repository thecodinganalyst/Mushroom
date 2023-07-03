package com.hevlar.mushroom.service

import com.hevlar.mushroom.controller.dto.MilkRecordingDto
import com.hevlar.mushroom.model.Child
import com.hevlar.mushroom.model.MilkRecording
import com.hevlar.mushroom.repository.MilkRecordingRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MilkRecordingService(val milkRecordingRepository: MilkRecordingRepository) {
    fun addMilkRecording(child: Child, dto: MilkRecordingDto): MilkRecording {
        val milkRecording = MilkRecording(0, dto.dateTime, dto.until, dto.type, dto.amount, child)
        return milkRecordingRepository.save(milkRecording)
    }

    fun editMilkRecording(id: Long , milkRecordingDto: MilkRecordingDto): MilkRecording {
        val milkRecording = milkRecordingRepository.findByIdOrNull(id) ?: throw Exception()
        milkRecording.until = milkRecordingDto.until
        milkRecording.type = milkRecordingDto.type
        milkRecording.amount = milkRecordingDto.amount
        return milkRecordingRepository.save(milkRecording)
    }

    fun listMilkRecordings(childId: Long): List<MilkRecording> {
        return milkRecordingRepository.findByChildId(childId)
    }

    fun getMilkRecording(id: Long): MilkRecording? {
        return milkRecordingRepository.findByIdOrNull(id)
    }

    fun deleteMilkRecording(id: Long) {
        milkRecordingRepository.deleteById(id)
    }
}
