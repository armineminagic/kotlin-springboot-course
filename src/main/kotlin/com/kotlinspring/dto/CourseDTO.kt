package com.kotlinspring.dto

import com.kotlinspring.enums.CATEGORY

data class CourseDTO(
    val id: Int?,
    val name: String,
    val category: CATEGORY,
    val instructorId: Int? = null,
)
