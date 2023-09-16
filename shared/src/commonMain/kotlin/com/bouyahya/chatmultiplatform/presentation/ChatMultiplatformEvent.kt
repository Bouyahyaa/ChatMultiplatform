package com.bouyahya.chatmultiplatform.presentation

import com.bouyahya.chatmultiplatform.domain.models.MessageData

sealed class ChatMultiplatformEvent {
    data class OnChangeUserName(val username: String) : ChatMultiplatformEvent()
    data class OnChangeMessageText(val text: String) : ChatMultiplatformEvent()
    data class Connect(val name: String) : ChatMultiplatformEvent()
    data class SendMessage(val messageData: MessageData) : ChatMultiplatformEvent()
}