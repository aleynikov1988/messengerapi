package com.example.messangerapi.components

import com.example.messangerapi.helpers.objects.ConversationListVO
import com.example.messangerapi.helpers.objects.ConversationVO
import com.example.messangerapi.helpers.objects.MessageVO
import com.example.messangerapi.models.Conversation
import com.example.messangerapi.services.ConversationServiceImpl
import org.springframework.stereotype.Component

@Component
class ConversationAssembler(
    val conversationService: ConversationServiceImpl,
    val messageAssembler: MessageAssembler
) {
    fun toConversationVO(
        conversation: Conversation,
        userId: Long
    ): ConversationVO {
        val messages: ArrayList<MessageVO> = ArrayList()

        conversation.messages?.mapTo(messages) {
            messageAssembler.toMessageVO(it)
        }

        return ConversationVO(
            conversation.id,
            conversationService.getUsernameInterlocutor(conversation, userId),
            messages
        )
    }

    fun toConversationListVO(
        conversations: List<Conversation>,
        userId: Long
    ): ConversationListVO {
        return ConversationListVO(
            conversations.map { toConversationVO(it, userId) }
        )
    }
}
