package com.hevlar.mushroom.service

import com.hevlar.mushroom.model.Child
import com.hevlar.mushroom.repository.ChildRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ChildService(val childRepository: ChildRepository) {

    fun addChild(child: Child): Child{
        return childRepository.save(child)
    }

    fun editChild(child: Child): Child {
        return childRepository.save(child)
    }

    fun listChildren(): List<Child> {
        return childRepository.findAll();
    }

    fun getChild(id: Long): Child? {
        return childRepository.findByIdOrNull(id)
    }

    fun deleteChild(id: Long) {
        childRepository.deleteById(id);
    }
}
