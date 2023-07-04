package com.hevlar.mushroom.controller

import com.hevlar.mushroom.controller.dto.DiaperChangeDto
import com.hevlar.mushroom.model.DiaperChangeRecording
import com.hevlar.mushroom.service.ChildService
import com.hevlar.mushroom.service.DiaperChangeService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/children/{childId}/diaper")
class DiaperChangeController(val diaperChangeService: DiaperChangeService, val childService: ChildService) {
    @PostMapping
    fun addDiaperChange(@PathVariable("childId") childId: Long, @RequestBody diaperChangeDto: DiaperChangeDto): DiaperChangeRecording {
        val child = childService.getChild(childId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return diaperChangeService.addDiaperChange(child, diaperChangeDto)
    }

    @GetMapping("/{id}")
    fun getDiaperChange(@PathVariable("childId") childId: Long, @PathVariable("id") id: Long): DiaperChangeRecording? {
        childService.getChild(childId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return diaperChangeService.getDiaperChange(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @GetMapping
    fun listDiaperChanges(@PathVariable("childId") childId: Long): List<DiaperChangeRecording> {
        childService.getChild(childId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return diaperChangeService.listDiaperChanges(childId)
    }

    @PutMapping("/{id}")
    fun editDiaperChange(@PathVariable("childId") childId: Long, @PathVariable("id") id: Long, @RequestBody diaperChangeDto: DiaperChangeDto): DiaperChangeRecording {
        childService.getChild(childId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return diaperChangeService.editDiaperChange(id, diaperChangeDto)
    }

    @DeleteMapping("/{id}")
    fun deleteDiaperChange(@PathVariable("childId") childId: Long, @PathVariable("id") id: Long){
        childService.getChild(childId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return diaperChangeService.deleteDiaperChange(id)
    }
}
