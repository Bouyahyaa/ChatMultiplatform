package com.bouyahya.chatmultiplatform.data

import com.bouyahya.chatmultiplatform.domain.models.MessageData
import com.bouyahya.chatmultiplatform.domain.repositories.ChatMultiplatformRepository
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ChatMultiplatformRepositoryImpl(
    private val httpClient: HttpClient,
) : ChatMultiplatformRepository {
    override suspend fun connect(username: String, userId: Long): WebSocketSession {
        println("ChatMultiplatformIsHere")
        return httpClient.webSocketSession(
            method = HttpMethod.Get,
            host = "172.20.10.8",
            port = 8080,
            path = "/chat/${userId}/${username}"
        )
    }

    override suspend fun send(session: WebSocketSession, messageData: MessageData) {
        session.send(Json.encodeToString(messageData))
    }
}
