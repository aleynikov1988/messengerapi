package com.example.messangerapi.services

import com.example.messangerapi.models.Conversation
import com.example.messangerapi.models.User

interface ConversationService {
    fun conversationExists(userA: User, userB: User): Boolean
    fun createConversation(userA: User, userB: User): Conversation
    fun getConversation(userA: User, userB: User): Conversation?
    fun retrieveThread(conversationId: Long): Conversation
    fun listConversationsByUserId(userId: Long): ArrayList<Conversation>
}
