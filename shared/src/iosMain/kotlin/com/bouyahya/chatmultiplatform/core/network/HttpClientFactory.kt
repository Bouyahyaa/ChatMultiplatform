package com.bouyahya.chatmultiplatform.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin

actual fun createPlatformHttpClient(): HttpClient {
    return HttpClient(Darwin)
}