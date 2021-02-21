package com.example.messangerapi.constants

enum class ResponseCode(val code: String) {
    SUCCESS("success"),
    ERROR("error"),
    USERNAME_UNAVAILABLE("usr_001"),
    INVALID_USER_ID("usr_002"),
    EMPTY_STATUS("usr_003"),
    MESSAGE_EMPTY("mes_001"),
    MESSAGE_RECIPIENT_INVALID("mes_002"),
    ACCOUNT_DEACTIVATED("glo_001")
}
