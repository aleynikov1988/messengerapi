package com.example.messangerapi.components

import com.example.messangerapi.constants.ErrorResponse
import com.example.messangerapi.constants.ResponseCode
import com.example.messangerapi.exceptions.InvalidUserIdException
import com.example.messangerapi.exceptions.UserStatusEmptyException
import com.example.messangerapi.exceptions.UsernameUnavailableException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class UserControllerAdvice {
    @ExceptionHandler(InvalidUserIdException::class)
    fun invalidId(exception: InvalidUserIdException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.badRequest().body(
            ErrorResponse(ResponseCode.INVALID_USER_ID.code, exception.message)
        )
    }

    @ExceptionHandler(UsernameUnavailableException::class)
    fun usernameUnavailable(exception: UsernameUnavailableException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.badRequest().body(
            ErrorResponse(ResponseCode.USERNAME_UNAVAILABLE.code, exception.message)
        )
    }

    @ExceptionHandler(UserStatusEmptyException::class)
    fun statusEmpty(exception: UserStatusEmptyException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.badRequest().body(
            ErrorResponse(ResponseCode.EMPTY_STATUS.code, exception.message)
        )
    }
}
