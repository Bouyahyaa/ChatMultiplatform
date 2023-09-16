package com.bouyahya.chatmultiplatform.domain.models

import io.ktor.websocket.*

data class User(
    val id: Long,
    val username: String,
    val session: WebSocketSession,
)