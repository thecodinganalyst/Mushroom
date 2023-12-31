package com.hevlar.mushroom.controller

import com.hevlar.mushroom.controller.dto.ChildDto
import com.hevlar.mushroom.model.Child
import com.hevlar.mushroom.service.ChildService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/children")
class ChildController(val childService: ChildService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addChild(@RequestBody childDto: ChildDto): Child {
        return childService.addChild(childDto)
    }

    @GetMapping("/{id}")
    fun getChild(@PathVariable("id") id: Long): Child? {
        return childService.getChild(id)
    }

    @GetMapping
    fun listChildren(): List<Child> {
        return childService.listChildren()
    }

    @PutMapping("/{id}")
    fun editChild(@PathVariable("id") id: Long, @RequestBody childDto: ChildDto): Child{
        return childService.editChild(id, childDto)
    }

    @DeleteMapping("/{id}")
    fun deleteChild(@PathVariable("id") id: Long){
        return childService.deleteChild(id)
    }

}
