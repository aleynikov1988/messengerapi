package com.example.messangerapi.services

import com.example.messangerapi.exceptions.ConversationIdInvalidException
import com.example.messangerapi.models.Conversation
import com.example.messangerapi.models.User
import com.example.messangerapi.repositories.ConversationRepository
import org.springframework.stereotype.Service

@Service
class ConversationServiceImpl(
    val repository: ConversationRepository
) : ConversationService {
    override fun conversationExists(userA: User, userB: User): Boolean {
        return if (repository.findBySenderRecipientId(userA.id, userB.id) != null) {
            true
        } else repository.findBySenderRecipientId(userB.id, userA.id) != null
    }

    override fun createConversation(userA: User, userB: User): Conversation {
        val conversation = Conversation(sender = userA, recipient = userB)
        repository.save(conversation)
        return conversation
    }

    override fun getConversation(userA: User, userB: User): Conversation? {
        return when {
            repository.findBySenderRecipientId(userA.id, userB.id) != null -> repository.findBySenderRecipientId(userA.id, userB.id)
            repository.findBySenderRecipientId(userB.id, userA.id) != null -> repository.findBySenderRecipientId(userB.id, userA.id)
            else -> null
        }
    }

    @Throws(ConversationIdInvalidException::class)
    override fun retrieveThread(conversationId: Long): Conversation {
        val record = repository.findById(conversationId)

        if (record.isPresent) {
            return record.get()
        }
        throw ConversationIdInvalidException("Invalid conversation id")
    }

    override fun listConversationsByUserId(userId: Long): ArrayList<Conversation> {
        val conversationList: ArrayList<Conversation> = ArrayList()

        conversationList.addAll(repository.findBySenderId(userId))
        conversationList.addAll(repository.findByRecipientId(userId))

        return conversationList
    }
}
