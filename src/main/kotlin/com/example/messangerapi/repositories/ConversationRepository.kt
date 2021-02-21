package com.example.messangerapi.repositories

import com.example.messangerapi.models.Conversation
import org.springframework.data.repository.CrudRepository

interface ConversationRepository : CrudRepository<Conversation, Long> {
    fun findBySenderId(id: Long): List<Conversation>
    fun findByRecipientId(id: Long): List<Conversation>
    fun findBySenderRecipientId(senderId: Long, recipientId: Long): Conversation?
}
