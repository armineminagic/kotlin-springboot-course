package com.kotlinspring.controller

import com.kotlinspring.dto.CourseDTO
import com.kotlinspring.entity.Course
import com.kotlinspring.service.CourseService
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/courses")
class CourseController(val courseService: CourseService) {

    companion object : KLogging()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addCourse(@RequestBody courseDTO: CourseDTO): CourseDTO {
        logger.info { "POST request: $courseDTO" }
        return courseService.addCourse(courseDTO)
    }

    @GetMapping
    fun getAllCourses() : List<CourseDTO> {
        return courseService.getAllCourses()
    }

    @PutMapping("/{course_id}")
    fun updateCourse(@RequestBody courseDTO: CourseDTO, @PathVariable("course_id") course_id: Int) : CourseDTO {
        return courseService.updateCourse(course_id, courseDTO)
    }
}
