package com.example.messangerapi.exceptions

import java.lang.RuntimeException

class InvalidUserIdException(override val message: String): RuntimeException()
class UsernameUnavailableException(override val message: String): RuntimeException()
class UserStatusEmptyException(override val message: String): RuntimeException()

class MessageEmptyException(override val message: String = "Message cannot be empty"): RuntimeException()
class MessageRecipientInvalidException(override val message: String): RuntimeException()

class ConversationIdInvalidException(override val message: String): RuntimeException()
