package com.hevlar.mushroom.service

import com.hevlar.mushroom.controller.dto.MilkRecordingDto
import com.hevlar.mushroom.model.Child
import com.hevlar.mushroom.model.MilkRecording
import com.hevlar.mushroom.repository.MilkRecordingRepository
import jakarta.validation.ConstraintViolationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import jakarta.validation.Validator

@Service
class MilkRecordingService(val milkRecordingRepository: MilkRecordingRepository, @Autowired val validator: Validator) {
    fun addMilkRecording(child: Child, dto: MilkRecordingDto): MilkRecording {
        val milkRecording = MilkRecording(0, dto.dateTime, dto.until, dto.type, dto.amount, child)
        val violations = validator.validate(milkRecording)
        if (violations.isNotEmpty()) {
            throw ConstraintViolationException(violations)
        }
        return milkRecordingRepository.save(milkRecording)
    }

    fun editMilkRecording(id: Long , milkRecordingDto: MilkRecordingDto): MilkRecording {
        val milkRecording = milkRecordingRepository.findByIdOrNull(id) ?: throw Exception()
        milkRecording.until = milkRecordingDto.until
        milkRecording.type = milkRecordingDto.type
        milkRecording.amount = milkRecordingDto.amount

        val violations = validator.validate(milkRecording)
        if (violations.isNotEmpty()) {
            throw ConstraintViolationException(violations)
        }

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
