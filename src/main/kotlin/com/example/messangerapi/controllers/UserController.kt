package com.example.messangerapi.controllers

import com.example.messangerapi.components.UserAssembler
import com.example.messangerapi.helpers.objects.UserListVO
import com.example.messangerapi.helpers.objects.UserVO
import com.example.messangerapi.models.User
import com.example.messangerapi.repositories.UserRepository
import com.example.messangerapi.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/users")
class UserController(
    val userService: UserService,
    val userRepository: UserRepository,
    val userAssembler: UserAssembler
) {
    @PostMapping
    @RequestMapping("/registrations")
    fun create(
        @RequestBody
        @Validated
        user: User
    ): ResponseEntity<UserVO> {
        return ResponseEntity.ok(
            userAssembler.toUserVO(userService.attemptRegistration(user))
        )
    }

    @GetMapping
    @RequestMapping("/{id}")
    fun show(
        @PathVariable("id")
        id: Long
    ): ResponseEntity<UserVO> {
        return ResponseEntity.ok(
            userAssembler.toUserVO(userService.getUserById(id) as User)
        )
    }

    @GetMapping
    fun index(request: HttpServletRequest): ResponseEntity<UserListVO> {
        val user = userRepository.findByUsername(request.userPrincipal.name) as User
        val users = userService.listUsers(user)

        return ResponseEntity.ok(
            userAssembler.toUserListVO(users)
        )
    }

    fun update(
        @RequestBody
        details: User,
        request: HttpServletRequest
    ): ResponseEntity<UserVO> {
        val user = userRepository.findByUsername(request.userPrincipal.name) as User

        userService.updateUserStatus(user, details.status)

        return ResponseEntity.ok(
            userAssembler.toUserVO(user)
        )
    }
}
