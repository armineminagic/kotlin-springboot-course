package com.kotlinspring.entity

import com.kotlinspring.enums.CATEGORY
import jakarta.persistence.*

@Entity
@Table(name = "Courses")
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    var name: String,
    @Enumerated(EnumType.STRING)
    var category: CATEGORY
)
