package com.hevlar.mushroom.service

import com.hevlar.mushroom.controller.dto.ChildDto
import com.hevlar.mushroom.model.Child
import com.hevlar.mushroom.repository.ChildRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ChildService(val childRepository: ChildRepository) {

    fun addChild(childDto: ChildDto): Child{
        val child = Child(0, childDto.name, childDto.dob)
        return childRepository.save(child)
    }

    fun editChild(id: Long, childDto: ChildDto): Child {
        val child = childRepository.findByIdOrNull(id) ?: throw Exception()
        child.name = childDto.name
        child.dob = childDto.dob
        return childRepository.save(child)
    }

    fun listChildren(): List<Child> {
        return childRepository.findAll()
    }

    fun getChild(id: Long): Child? {
        return childRepository.findByIdOrNull(id)
    }

    fun deleteChild(id: Long) {
        childRepository.deleteById(id)
    }
}
