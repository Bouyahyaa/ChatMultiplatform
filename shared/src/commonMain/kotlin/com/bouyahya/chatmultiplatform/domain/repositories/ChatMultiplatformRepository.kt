package com.bouyahya.chatmultiplatform.domain.repositories

interface ChatMultiplatformRepository {
    suspend fun chat()

    suspend fun send(message: String)
}