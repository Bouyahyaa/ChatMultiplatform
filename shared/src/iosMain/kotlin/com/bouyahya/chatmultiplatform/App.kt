package com.bouyahya.chatmultiplatform

import androidx.compose.ui.window.ComposeUIViewController
import com.bouyahya.chatmultiplatform.di.ViewModels

fun MainViewController() = ComposeUIViewController {
    val viewModel = ViewModels.getChatMultiplatformViewModel()
    App(
        chatMultiplatformViewModel = viewModel
    )
}