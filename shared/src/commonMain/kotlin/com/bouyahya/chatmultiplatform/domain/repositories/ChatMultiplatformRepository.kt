package com.bouyahya.chatmultiplatform.domain.repositories

import com.bouyahya.chatmultiplatform.domain.models.MessageData
import com.bouyahya.chatmultiplatform.domain.models.User
import io.ktor.websocket.*

interface ChatMultiplatformRepository {
    suspend fun connect(username: String, userId: Long, profileImage: String): WebSocketSession

    suspend fun send(session: WebSocketSession, messageData: MessageData)
}