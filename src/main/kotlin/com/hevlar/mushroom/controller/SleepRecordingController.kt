package com.hevlar.mushroom.controller

import com.hevlar.mushroom.controller.dto.SleepRecordingDto
import com.hevlar.mushroom.model.SleepRecording
import com.hevlar.mushroom.service.ChildService
import com.hevlar.mushroom.service.SleepRecordingService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/child/{childId}/sleep")
class SleepRecordingController(val sleepRecordingService: SleepRecordingService, val childService: ChildService){
    @PostMapping
    fun addMilkRecording(@PathVariable("childId") childId: Long, @RequestBody sleepRecordingDto: SleepRecordingDto): SleepRecording {
        val child = childService.getChild(childId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return sleepRecordingService.addSleepRecord(child, sleepRecordingDto)
    }

    @GetMapping("/{id}")
    fun getMilkRecording(@PathVariable("childId") childId: Long, @PathVariable("id") id: Long): SleepRecording? {
        childService.getChild(childId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return sleepRecordingService.getSleepRecord(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @GetMapping
    fun listMilkRecording(@PathVariable("childId") childId: Long): List<SleepRecording> {
        childService.getChild(childId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return sleepRecordingService.listSleepRecords(childId)
    }

    @PutMapping("/{id}")
    fun editMilkRecording(@PathVariable("childId") childId: Long, @PathVariable("id") id: Long, @RequestBody sleepRecordingDto: SleepRecordingDto): SleepRecording {
        childService.getChild(childId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return sleepRecordingService.editSleepRecord(id, sleepRecordingDto)
    }

    @DeleteMapping("/{id}")
    fun deleteMilkRecording(@PathVariable("childId") childId: Long, @PathVariable("id") id: Long){
        childService.getChild(childId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return sleepRecordingService.deleteSleepRecord(id)
    }
}
