package com.bouyahya.chatmultiplatform.domain.repositories

import com.bouyahya.chatmultiplatform.domain.models.User

interface ChatMultiplatformRepository {
    suspend fun connect(username: String, userId: Long): User

    suspend fun send(user: User, message: String)
}