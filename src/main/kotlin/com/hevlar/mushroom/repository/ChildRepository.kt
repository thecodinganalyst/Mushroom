package com.hevlar.mushroom.repository

import com.hevlar.mushroom.model.Child
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChildRepository: JpaRepository<Child, Long> {
}
