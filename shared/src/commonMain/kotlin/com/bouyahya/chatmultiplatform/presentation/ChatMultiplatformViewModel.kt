package com.bouyahya.chatmultiplatform.presentation

import com.bouyahya.chatmultiplatform.core.utils.Resource
import com.bouyahya.chatmultiplatform.domain.usecases.ChatMultiplatformUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatMultiplatformViewModel(
    private val chatMultiplatformUseCase: ChatMultiplatformUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<ChatMultiplatformState>(ChatMultiplatformState())
    var state = _state.asStateFlow()

    fun onEvent(event: ChatMultiplatformEvent) {
        when (event) {

            is ChatMultiplatformEvent.OnChangeUserName -> {
                _state.update {
                    it.copy(username = event.username)
                }
            }

            is ChatMultiplatformEvent.Connect -> {
                connect()
            }

            is ChatMultiplatformEvent.SendMessage -> {
                viewModelScope.launch {
                    chatMultiplatformUseCase.invoke(event.message)
                }
            }
        }
    }

    private fun connect() {
        viewModelScope.launch {
            chatMultiplatformUseCase.invoke().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        println("Connection Success")
                        _state.update {
                            it.copy(isConnected = true)
                        }
                    }

                    is Resource.Error -> {
                        println("Connection Error")
                    }

                    is Resource.Loading -> {
                        println("Connection Loading")
                    }
                }
            }
        }
    }
}