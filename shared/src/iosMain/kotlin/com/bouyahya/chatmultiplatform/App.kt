package com.bouyahya.chatmultiplatform

import androidx.compose.ui.window.ComposeUIViewController
import com.bouyahya.chatmultiplatform.di.ViewModels
import com.bouyahya.chatmultiplatform.presentation.ChatMultiplatformScreen

fun MainViewController() = ComposeUIViewController {
    val viewModel = ViewModels.getChatMultiplatformViewModel()
    ChatMultiplatformScreen(
        chatMultiplatformViewModel = viewModel
    )
}