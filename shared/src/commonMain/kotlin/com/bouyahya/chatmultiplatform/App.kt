package com.bouyahya.chatmultiplatform

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bouyahya.chatmultiplatform.presentation.ChatMultiplatformViewModel

@Composable
fun App(
    chatMultiplatformViewModel: ChatMultiplatformViewModel,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Chat Multiplatform"
        )
    }
}