package com.bouyahya.chatmultiplatform.data

import com.bouyahya.chatmultiplatform.domain.models.User
import com.bouyahya.chatmultiplatform.domain.repositories.ChatMultiplatformRepository
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*

class ChatMultiplatformRepositoryImpl(
    private val httpClient: HttpClient,
) : ChatMultiplatformRepository {
    private var session: WebSocketSession? = null
    private var user: User? = null
    override suspend fun chat() {
        println("ChatMultiplatformIsHere")
        session = httpClient.webSocketSession(
            method = HttpMethod.Get,
            host = "192.168.120.62",
            port = 8080,
            path = "/chat"
        )
    }

    override suspend fun send(message: String) {
        session?.send(message)
    }
}
