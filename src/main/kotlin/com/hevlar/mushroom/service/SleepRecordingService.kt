package com.hevlar.mushroom.service

import com.hevlar.mushroom.model.SleepRecording
import com.hevlar.mushroom.repository.SleepRecordingRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SleepRecordingService(val sleepRecordingRepository: SleepRecordingRepository) {

    fun addSleepRecord(sleepRecording: SleepRecording): SleepRecording {
        return sleepRecordingRepository.save(sleepRecording)
    }

    fun editSleepRecord(sleepRecording: SleepRecording): SleepRecording {
        return sleepRecordingRepository.save(sleepRecording)
    }

    fun listSleepRecords(): List<SleepRecording> {
        return sleepRecordingRepository.findAll();
    }

    fun getSleepRecord(id: Long): SleepRecording? {
        return sleepRecordingRepository.findByIdOrNull(id);
    }

    fun deleteSleepRecord(id: Long) {
        sleepRecordingRepository.deleteById(id);
    }
}
