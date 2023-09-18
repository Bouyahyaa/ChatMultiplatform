package com.bouyahya.chatmultiplatform.presentation

import com.bouyahya.chatmultiplatform.domain.models.MessageData
import com.bouyahya.chatmultiplatform.domain.models.User
import io.ktor.websocket.*

data class ChatMultiplatformState(
    val usernameText: String = "",
    val messageText: String = "",
    val currentUser: User? = null,
    val connectedUsers: List<User> = emptyList(),
    val session: WebSocketSession? = null,
    val messages: List<MessageData> = emptyList(),
)