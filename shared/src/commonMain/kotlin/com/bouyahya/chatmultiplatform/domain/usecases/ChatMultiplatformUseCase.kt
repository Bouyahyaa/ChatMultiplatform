package com.bouyahya.chatmultiplatform.domain.usecases

import com.bouyahya.chatmultiplatform.core.utils.Resource
import com.bouyahya.chatmultiplatform.domain.repositories.ChatMultiplatformRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ChatMultiplatformUseCase(
    private val chatMultiplatformRepository: ChatMultiplatformRepository,
) {
    suspend operator fun invoke(): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading<Unit>())
            chatMultiplatformRepository.chat()
            emit(Resource.Success<Unit>(Unit))
        } catch (e: Exception) {
            emit(Resource.Error<Unit>(e.message ?: "An unexpected error occur"))
        }
    }

    suspend operator fun invoke(message: String) {
        chatMultiplatformRepository.send(message)
    }
}