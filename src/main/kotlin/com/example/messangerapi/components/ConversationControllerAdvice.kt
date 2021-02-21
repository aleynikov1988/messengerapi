package com.example.messangerapi.components

import com.example.messangerapi.constants.ErrorResponse
import com.example.messangerapi.constants.ResponseCode
import com.example.messangerapi.exceptions.ConversationIdInvalidException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ConversationControllerAdvice {
    @ExceptionHandler(ConversationIdInvalidException::class)
    fun invalidId(exception: ConversationIdInvalidException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.badRequest().body(
            ErrorResponse(ResponseCode.ERROR.code, exception.message)
        )
    }
}
