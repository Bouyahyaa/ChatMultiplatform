package com.bouyahya.chatmultiplatform.presentation

import com.bouyahya.chatmultiplatform.domain.usecases.ChatMultiplatformUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch

class ChatMultiplatformViewModel(
    private val chatMultiplatformUseCase: ChatMultiplatformUseCase,
) : ViewModel() {

    init {
        viewModelScope.launch {
            chatMultiplatformUseCase.invoke()
        }
    }
}