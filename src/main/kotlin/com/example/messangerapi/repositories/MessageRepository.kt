package com.example.messangerapi.repositories

import com.example.messangerapi.models.Message
import org.springframework.data.repository.CrudRepository

interface MessageRepository : CrudRepository<Message, Long> {
    fun findByConversationId(id: Long): List<Message>
}
