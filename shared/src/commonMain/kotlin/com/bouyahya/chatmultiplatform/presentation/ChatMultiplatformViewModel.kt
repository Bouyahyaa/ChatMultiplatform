package com.bouyahya.chatmultiplatform.presentation

import com.bouyahya.chatmultiplatform.core.utils.Resource
import com.bouyahya.chatmultiplatform.domain.models.MessageData
import com.bouyahya.chatmultiplatform.domain.models.User
import com.bouyahya.chatmultiplatform.domain.usecases.ChatMultiplatformUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.websocket.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.math.abs

class ChatMultiplatformViewModel(
    private val chatMultiplatformUseCase: ChatMultiplatformUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<ChatMultiplatformState>(ChatMultiplatformState())
    var state = _state.asStateFlow()
    fun onEvent(event: ChatMultiplatformEvent) {
        when (event) {

            is ChatMultiplatformEvent.OnChangeUserName -> {
                _state.update {
                    it.copy(usernameText = event.username)
                }
            }

            is ChatMultiplatformEvent.OnChangeMessageText -> {
                _state.update {
                    it.copy(messageText = event.text)
                }
            }

            is ChatMultiplatformEvent.Connect -> {
                connect()
            }

            is ChatMultiplatformEvent.SendMessage -> {
                viewModelScope.launch {
                    val messageData = MessageData(
                        id = abs((0..999999999999).random()),
                        message = event.messageText,
                        senderId = _state.value.currentUser!!.id
                    )
                    chatMultiplatformUseCase.invoke(_state.value.session!!, messageData)
                }
            }
        }
    }

    private fun connect() {
        viewModelScope.launch {
            val username = _state.value.usernameText
            val userId = abs((0..999999999999).random())
            chatMultiplatformUseCase.invoke(
                username = username,
                userId = userId,
            ).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        println("Connection Success")
                        val currentUser = User(
                            id = userId,
                            username = username
                        )
                        _state.update {
                            it.copy(
                                currentUser = currentUser,
                                session = result.data,
                            )
                        }
                        receiveMessages()
                    }

                    is Resource.Error -> {
                        println("Connection Error : ${result.message}")
                    }

                    is Resource.Loading -> {
                        println("Connection Loading")
                    }
                }
            }
        }
    }

    private fun receiveMessages() {
        viewModelScope.launch {
            while (true) {
                val frame = _state.value.session?.incoming?.receive()
                if (frame is Frame.Text) {
                    println(frame.readText())
                    if (frame.readText() != "You are connected!") {
                        val messageData = Json.decodeFromString<MessageData>(frame.readText())
                        _state.update {
                            it.copy(
                                messages = _state.value.messages.plus(messageData),
                                messageText = ""
                            )
                        }
                    }
                }
            }
        }
    }
}