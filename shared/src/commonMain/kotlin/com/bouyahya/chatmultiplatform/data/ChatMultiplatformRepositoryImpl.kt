package com.bouyahya.chatmultiplatform.data

import com.bouyahya.chatmultiplatform.domain.models.MessageData
import com.bouyahya.chatmultiplatform.domain.models.User
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
    override suspend fun connect(username: String, userId: Long): User {
        println("ChatMultiplatformIsHere")
        return User(
            id = userId,
            username = username,
            session = httpClient.webSocketSession(
                method = HttpMethod.Get,
                host = "192.168.120.62",
                port = 8080,
                path = "/chat"
            )
        )
    }

    override suspend fun send(user: User, messageData: MessageData) {
        user.session.send(Json.encodeToString(messageData))
    }
}
