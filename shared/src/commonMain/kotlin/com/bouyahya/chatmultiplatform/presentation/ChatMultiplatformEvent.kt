package com.bouyahya.chatmultiplatform.presentation

sealed class ChatMultiplatformEvent {
    data class OnChangeUserName(val username: String) : ChatMultiplatformEvent()
    data class Connect(val name: String) : ChatMultiplatformEvent()
    data class SendMessage(val message: String) : ChatMultiplatformEvent()
}