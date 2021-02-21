package com.example.messangerapi.services

import com.example.messangerapi.models.Message
import com.example.messangerapi.models.User

interface MesageService {
    fun sendMessage(sender: User, recipientId: Long, messageText: String): Message
}
