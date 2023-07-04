package com.hevlar.mushroom.controller

import com.hevlar.mushroom.controller.dto.MilkRecordingDto
import com.hevlar.mushroom.model.MilkRecording
import com.hevlar.mushroom.service.ChildService
import com.hevlar.mushroom.service.MilkRecordingService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/children/{childId}/milk")
class MilkRecordingController(val milkRecordingService: MilkRecordingService, val childService: ChildService) {

    @PostMapping
    fun addMilkRecording(@PathVariable("childId") childId: Long, @RequestBody milkRecordingDto: MilkRecordingDto): MilkRecording {
        val child = childService.getChild(childId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return milkRecordingService.addMilkRecording(child, milkRecordingDto)
    }

    @GetMapping("/{id}")
    fun getMilkRecording(@PathVariable("childId") childId: Long, @PathVariable("id") id: Long): MilkRecording? {
        childService.getChild(childId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return milkRecordingService.getMilkRecording(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @GetMapping
    fun listMilkRecording(@PathVariable("childId") childId: Long): List<MilkRecording> {
        childService.getChild(childId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return milkRecordingService.listMilkRecordings(childId)
    }

    @PutMapping("/{id}")
    fun editMilkRecording(@PathVariable("childId") childId: Long, @PathVariable("id") id: Long, @RequestBody milkRecordingDto: MilkRecordingDto): MilkRecording {
        childService.getChild(childId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return milkRecordingService.editMilkRecording(id, milkRecordingDto)
    }

    @DeleteMapping("/{id}")
    fun deleteMilkRecording(@PathVariable("childId") childId: Long, @PathVariable("id") id: Long){
        childService.getChild(childId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return milkRecordingService.deleteMilkRecording(id)
    }
}
