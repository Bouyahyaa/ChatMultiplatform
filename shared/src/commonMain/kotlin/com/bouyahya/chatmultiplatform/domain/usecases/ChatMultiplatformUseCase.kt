package com.bouyahya.chatmultiplatform.domain.usecases

import com.bouyahya.chatmultiplatform.domain.repositories.ChatMultiplatformRepository

class ChatMultiplatformUseCase(
    private val chatMultiplatformRepository: ChatMultiplatformRepository,
) {
    suspend operator fun invoke() {
        chatMultiplatformRepository.chat()
    }
}