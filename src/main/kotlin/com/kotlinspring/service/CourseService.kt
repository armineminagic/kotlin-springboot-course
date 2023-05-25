package com.kotlinspring.service

import com.kotlinspring.dto.CourseDTO
import com.kotlinspring.entity.Course
import com.kotlinspring.exceptions.InstructorNotValidException
import com.kotlinspring.repository.CourseRepository
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class CourseService(
    val courseRepository: CourseRepository,
    val instructorService: InstructorService) {

    companion object : KLogging()

    fun addCourse(courseDTO: CourseDTO): CourseDTO {

        val instructor = instructorService.findByInstructorId(courseDTO.instructorId!!)

        if (!instructor.isPresent) {
            logger.warn { "INSTRUCTOR IS NOT PRESENTED!" }
            throw InstructorNotValidException("Instructor Not valid for the ID: ${courseDTO.instructorId}")
        }

        val courseEntity = courseDTO.let {
            Course(null, it.name, it.category, instructor.get())
        }
        courseRepository.save(courseEntity)

        logger.info { "Saved course is: $courseEntity" }

        return courseEntity.let {
            CourseDTO(it.id, it.name, it.category, it.instructor!!.id)
        }
    }

    fun getAllCourses(courseName: String?): List<CourseDTO> {

        val course = courseName?.let {
            courseRepository.findCoursesByName(courseName)
        } ?: courseRepository.findAll()

        return course.map { CourseDTO(it.id, it.name, it.category) }
    }

    fun updateCourse(courseId: Int, courseDTO: CourseDTO): CourseDTO {
        val course = courseRepository.findById(courseId)
        return if (course.isPresent) {
            logger.info { "Editing course: $course" }
            course.get()
                .let {
                    it.name = courseDTO.name
                    it.category = courseDTO.category
                    courseRepository.save(it)
                    CourseDTO(it.id, it.name, it.category)
                }
        } else {
            throw Exception("Course with provided ID not exists!")
        }
    }

    fun deleteCourse(courseId: Int) {
        val course = courseRepository.findById(courseId)
        if (course.isPresent) {
            logger.info { "Removing course: $course" }
            course.get()
                .let {
                    courseRepository.deleteById(it.id!!)
                }
        } else {
            throw Exception("Course not found or cannot be deleted!")
        }

    }
}
