package com.hevlar.mushroom.service

import com.hevlar.mushroom.model.DiaperChangeRecording
import com.hevlar.mushroom.repository.DiaperChangeRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class DiaperChangeService(val diaperChangeRepository: DiaperChangeRepository) {
    fun addDiaperChange(diaperChangeRecording: DiaperChangeRecording): DiaperChangeRecording{
        return diaperChangeRepository.save(diaperChangeRecording)
    }

    fun editDiaperChange(diaperChangeRecording: DiaperChangeRecording): DiaperChangeRecording{
        return diaperChangeRepository.save(diaperChangeRecording)
    }

    fun listDiaperChanges(): List<DiaperChangeRecording> {
        return diaperChangeRepository.findAll()
    }

    fun getDiaperChange(id: Long): DiaperChangeRecording? {
        return diaperChangeRepository.findByIdOrNull(id);
    }

    fun deleteDiaperChange(id: Long) {
        diaperChangeRepository.deleteById(id);
    }
}
