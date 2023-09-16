package com.bouyahya.chatmultiplatform.presentation

import com.bouyahya.chatmultiplatform.domain.models.MessageData
import com.bouyahya.chatmultiplatform.domain.models.User

data class ChatMultiplatformState(
    val usernameText: String = "",
    val messageText: String = "",
    val user: User? = null,
    val messages: List<MessageData> = emptyList(),
)