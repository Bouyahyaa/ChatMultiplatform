package com.bouyahya.chatmultiplatform.domain.usecases

import com.bouyahya.chatmultiplatform.core.utils.Resource
import com.bouyahya.chatmultiplatform.domain.models.MessageData
import com.bouyahya.chatmultiplatform.domain.models.User
import com.bouyahya.chatmultiplatform.domain.repositories.ChatMultiplatformRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ChatMultiplatformUseCase(
    private val chatMultiplatformRepository: ChatMultiplatformRepository,
) {
    suspend operator fun invoke(
        username: String,
        userId: Long,
    ): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading<User>())
            val user = chatMultiplatformRepository.connect(username, userId)
            emit(Resource.Success<User>(user))
        } catch (e: Exception) {
            emit(Resource.Error<User>(e.message ?: "An unexpected error occur"))
        }
    }

    suspend operator fun invoke(user: User, messageData: MessageData) {
        chatMultiplatformRepository.send(user, messageData)
    }
}