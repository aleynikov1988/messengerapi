package com.example.messangerapi.services

import com.example.messangerapi.exceptions.MessageEmptyException
import com.example.messangerapi.exceptions.MessageRecipientInvalidException
import com.example.messangerapi.models.Conversation
import com.example.messangerapi.models.Message
import com.example.messangerapi.models.User
import com.example.messangerapi.repositories.ConversationRepository
import com.example.messangerapi.repositories.MessageRepository
import com.example.messangerapi.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl(
    val repository: MessageRepository,
    val conversationRepository: ConversationRepository,
    val userRepository: UserRepository,
    val conversationService: ConversationService
) : MesageService {
    @Throws(
        MessageEmptyException::class,
        MessageRecipientInvalidException::class
    )
    override fun sendMessage(sender: User, recipientId: Long, messageText: String): Message {
        val record = userRepository.findById(recipientId)

        if (record.isPresent) {
            val recipient = record.get()

            if (!messageText.isEmpty()) {
                val conversation: Conversation = if (conversationService.conversationExists(sender, recipient)) {
                    conversationService.getConversation(sender, recipient) as Conversation
                } else {
                    conversationService.createConversation(sender, recipient)
                }
                conversationRepository.save(conversation)

                val message = Message(
                    sender = sender,
                    recipient = recipient,
                    body = messageText,
                    conversation = conversation
                )
                repository.save(message)
                return message
            }

            throw MessageEmptyException()
        }
        throw MessageRecipientInvalidException("The recipient is invalid")
    }
}
