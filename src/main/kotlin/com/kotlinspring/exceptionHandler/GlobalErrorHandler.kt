package com.kotlinspring.exceptionHandler

import com.kotlinspring.exceptions.InstructorNotValidException
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@Component
@ControllerAdvice
class GlobalErrorHandler : ResponseEntityExceptionHandler() {

    companion object : KLogging()

    @ExceptionHandler(InstructorNotValidException::class)
    fun handleAllExceptions(ex: InstructorNotValidException, request: WebRequest) : ResponseEntity<Any> {
        logger.error("Exception observed : ${ex.message}", ex)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ex.message)
    }
}
