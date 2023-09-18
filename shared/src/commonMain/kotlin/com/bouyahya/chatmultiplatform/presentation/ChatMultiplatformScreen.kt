package com.bouyahya.chatmultiplatform.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.bouyahya.chatmultiplatform.presentation.components.ChatComponent
import com.bouyahya.chatmultiplatform.presentation.components.ConnectComponent

@Composable
fun ChatMultiplatformScreen(
    chatMultiplatformViewModel: ChatMultiplatformViewModel,
) {
    val state by chatMultiplatformViewModel.state.collectAsState()

    if (state.currentUser != null) {
        ChatComponent(
            state = state,
            chatMultiplatformViewModel = chatMultiplatformViewModel
        )
    } else {
        ConnectComponent(
            state = state,
            chatMultiplatformViewModel = chatMultiplatformViewModel
        )
    }
}