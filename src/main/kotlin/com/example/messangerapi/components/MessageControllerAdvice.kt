package com.example.messangerapi.components

import com.example.messangerapi.constants.ErrorResponse
import com.example.messangerapi.constants.ResponseCode
import com.example.messangerapi.exceptions.MessageEmptyException
import com.example.messangerapi.exceptions.MessageRecipientInvalidException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class MessageControllerAdvice {
    @ExceptionHandler(MessageEmptyException::class)
    fun empty(exception: MessageEmptyException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.unprocessableEntity().body(
            ErrorResponse(ResponseCode.MESSAGE_EMPTY.code, exception.message)
        )
    }

    @ExceptionHandler(MessageRecipientInvalidException::class)
    fun recipientInvalid(exception: MessageRecipientInvalidException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.unprocessableEntity().body(
            ErrorResponse(ResponseCode.MESSAGE_RECIPIENT_INVALID.code, exception.message)
        )
    }
}