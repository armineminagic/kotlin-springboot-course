package com.kotlinspring.entity

import com.kotlinspring.enums.CATEGORY
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "Courses")
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    var name: String,
    @Enumerated(EnumType.STRING)
    var category: CATEGORY,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INSTRUCTOR_ID", nullable = false)
    @get:NotNull(message = "courseDTO.instructorId must not be null")
    var instructor: Instructor? = null
) {
    override fun toString(): String {
        return "Course(id=$id, name='$name', category='$category', instructor=${instructor!!.id})"
    }
}
