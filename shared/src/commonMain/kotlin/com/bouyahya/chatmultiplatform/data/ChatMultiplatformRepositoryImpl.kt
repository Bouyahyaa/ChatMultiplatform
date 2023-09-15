package com.bouyahya.chatmultiplatform.data

import com.bouyahya.chatmultiplatform.domain.repositories.ChatMultiplatformRepository
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*

class ChatMultiplatformRepositoryImpl(
    private val httpClient: HttpClient,
) : ChatMultiplatformRepository {

    override suspend fun chat() {
        println("ChatMultiplatformIsHere")
        httpClient.webSocket(
            method = HttpMethod.Get,
            host = "172.20.10.5",
            port = 8080,
            path = "/chat"
        ) {}
    }
}
