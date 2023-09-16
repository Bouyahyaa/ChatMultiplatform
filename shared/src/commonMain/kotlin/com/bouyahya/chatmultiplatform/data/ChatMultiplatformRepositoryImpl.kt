package com.bouyahya.chatmultiplatform.data

import com.bouyahya.chatmultiplatform.domain.repositories.ChatMultiplatformRepository
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*

class ChatMultiplatformRepositoryImpl(
    private val httpClient: HttpClient,
) : ChatMultiplatformRepository {
    private var session: WebSocketSession? = null
    override suspend fun chat() {
        println("ChatMultiplatformIsHere")
        session = httpClient.webSocketSession(
            method = HttpMethod.Get,
            host = "172.20.10.5",
            port = 8080,
            path = "/chat"
        )
        while (true) {
            val frame = session?.incoming?.receive()
            if (frame is Frame.Text) {
                println(frame.readText())
            }
        }
    }

    override suspend fun send(message: String) {
        session?.send(message)
    }
}
