package com.hevlar.mushroom.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name="Child")
class Child (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    var name: String,
    var dob: LocalDate,
){
}
