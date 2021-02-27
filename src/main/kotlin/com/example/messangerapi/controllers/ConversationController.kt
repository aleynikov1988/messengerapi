package com.example.messangerapi.controllers

import com.example.messangerapi.components.ConversationAssembler
import com.example.messangerapi.helpers.objects.ConversationListVO
import com.example.messangerapi.helpers.objects.ConversationVO
import com.example.messangerapi.models.User
import com.example.messangerapi.repositories.UserRepository
import com.example.messangerapi.services.ConversationServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/conversations")
class ConversationController(
    val conversationService: ConversationServiceImpl,
    val conversationAssembler: ConversationAssembler,
    val userRepository: UserRepository
) {
    @GetMapping
    fun list(request: HttpServletRequest): ResponseEntity<ConversationListVO> {
        val user = userRepository.findByUsername(request.userPrincipal.name) as User
        val conversations = conversationService.listConversationsByUserId(user.id)

        return ResponseEntity.ok(
            conversationAssembler.toConversationListVO(conversations, user.id)
        )
    }

    @GetMapping
    @RequestMapping("/{id}")
    fun show(
        @PathVariable(name = "id")
        id: Long,
        request: HttpServletRequest
    ): ResponseEntity<ConversationVO> {
        val user = userRepository.findByUsername(request.userPrincipal.name) as User

        return ResponseEntity.ok(
            conversationAssembler.toConversationVO(conversationService.retrieveThread(id), user.id)
        )
    }
}
