package com.hevlar.mushroom.service

import com.hevlar.mushroom.controller.dto.SleepRecordingDto
import com.hevlar.mushroom.model.Child
import com.hevlar.mushroom.model.SleepRecording
import com.hevlar.mushroom.repository.SleepRecordingRepository
import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.LocalDateTime

@Service
class SleepRecordingService(val sleepRecordingRepository: SleepRecordingRepository, @Autowired val validator: Validator) {

    fun addSleepRecord(child: Child, sleepRecordingDto: SleepRecordingDto): SleepRecording {
        val sleepRecording = SleepRecording(0, sleepRecordingDto.dateTime, sleepRecordingDto.until, child)

        val violations = validator.validate(sleepRecording)
        if (violations.isNotEmpty()) {
            throw ConstraintViolationException(violations)
        }

        return sleepRecordingRepository.save(sleepRecording)
    }

    fun editSleepRecord(id: Long, sleepRecordingDto: SleepRecordingDto): SleepRecording {
        val sleepRecording = sleepRecordingRepository.findByIdOrNull(id) ?: throw Exception()
        sleepRecording.dateTime = sleepRecordingDto.dateTime
        sleepRecording.until = sleepRecordingDto.until

        val violations = validator.validate(sleepRecording)
        if (violations.isNotEmpty()) {
            throw ConstraintViolationException(violations)
        }

        return sleepRecordingRepository.save(sleepRecording)
    }

    fun listSleepRecords(childId: Long): List<SleepRecording> {
        val result = sleepRecordingRepository.findAllByChildId(childId)
        return result
    }

    fun getSleepRecord(id: Long): SleepRecording? {
        return sleepRecordingRepository.findByIdOrNull(id)
    }

    fun deleteSleepRecord(id: Long) {
        sleepRecordingRepository.deleteById(id)
    }

    fun isChildAsleep(childId: Long, @Value("\${child.sleep.max.hours}") maxSleepHours: Int): Boolean{
        val lastSleepRecord = sleepRecordingRepository.findTopByChildIdOrderByDateTimeDesc(childId) ?: throw Exception()
        return if (lastSleepRecord.until != null){
            false
        } else Duration.between(lastSleepRecord.dateTime, LocalDateTime.now()).toHoursPart() <= maxSleepHours
    }
}
