package com.hevlar.mushroom.service

import com.hevlar.mushroom.controller.dto.DiaperChangeDto
import com.hevlar.mushroom.model.Child
import com.hevlar.mushroom.model.DiaperChangeRecording
import com.hevlar.mushroom.repository.DiaperChangeRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class DiaperChangeService(val diaperChangeRepository: DiaperChangeRepository) {
    fun addDiaperChange(child: Child, diaperChangeDto: DiaperChangeDto): DiaperChangeRecording{
        val diaperChangeRecording = DiaperChangeRecording(0, diaperChangeDto.dateTime, diaperChangeDto.diaperStatus, child)
        return diaperChangeRepository.save(diaperChangeRecording)
    }

    fun editDiaperChange(id: Long, diaperChangeDto: DiaperChangeDto): DiaperChangeRecording{
        val diaperChangeRecording = diaperChangeRepository.findByIdOrNull(id) ?: throw Exception()
        diaperChangeRecording.dateTime = diaperChangeDto.dateTime
        diaperChangeRecording.diaperStatus = diaperChangeDto.diaperStatus
        return diaperChangeRepository.save(diaperChangeRecording)
    }

    fun listDiaperChanges(childId: Long): List<DiaperChangeRecording> {
        return diaperChangeRepository.findByChildId(childId)
    }

    fun getDiaperChange(id: Long): DiaperChangeRecording? {
        return diaperChangeRepository.findByIdOrNull(id)
    }

    fun deleteDiaperChange(id: Long) {
        diaperChangeRepository.deleteById(id)
    }
}
