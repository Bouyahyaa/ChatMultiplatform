package com.bouyahya.chatmultiplatform.domain.usecases

import com.bouyahya.chatmultiplatform.core.utils.Resource
import com.bouyahya.chatmultiplatform.domain.models.MessageData
import com.bouyahya.chatmultiplatform.domain.repositories.ChatMultiplatformRepository
import io.ktor.websocket.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ChatMultiplatformUseCase(
    private val chatMultiplatformRepository: ChatMultiplatformRepository,
) {
    suspend operator fun invoke(
        username: String,
        userId: Long,
        profilePicture: String,
    ): Flow<Resource<WebSocketSession>> = flow {
        try {
            emit(Resource.Loading<WebSocketSession>())
            val webSocketSession =
                chatMultiplatformRepository.connect(username, userId, profilePicture)
            emit(Resource.Success<WebSocketSession>(webSocketSession))
        } catch (e: Exception) {
            emit(Resource.Error<WebSocketSession>(e.message ?: "An unexpected error occur"))
        }
    }

    suspend operator fun invoke(session: WebSocketSession, messageData: MessageData) {
        chatMultiplatformRepository.send(session, messageData)
    }
}