package com.bouyahya.chatmultiplatform.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ChatMultiplatformScreen(
    chatMultiplatformViewModel: ChatMultiplatformViewModel,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center).clickable {
                chatMultiplatformViewModel.sendMessage()
            },
            text = "Chat Multiplatform"
        )
    }
}