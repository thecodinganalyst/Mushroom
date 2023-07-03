package com.hevlar.mushroom.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name="Child")
class Child (
    @Id
    @GeneratedValue
    val id: Long,
    var name: String,
    var dob: LocalDate,
){
}
