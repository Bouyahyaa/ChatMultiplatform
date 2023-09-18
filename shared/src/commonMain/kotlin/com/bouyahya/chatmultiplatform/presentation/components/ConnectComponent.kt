package com.bouyahya.chatmultiplatform.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bouyahya.chatmultiplatform.presentation.ChatMultiplatformEvent
import com.bouyahya.chatmultiplatform.presentation.ChatMultiplatformState
import com.bouyahya.chatmultiplatform.presentation.ChatMultiplatformViewModel

@Composable
fun ConnectComponent(
    state: ChatMultiplatformState,
    chatMultiplatformViewModel: ChatMultiplatformViewModel,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = state.usernameText,
                onValueChange = {
                    chatMultiplatformViewModel.onEvent(
                        ChatMultiplatformEvent.OnChangeUserName(
                            it
                        )
                    )
                }
            )

            Button(
                enabled = state.usernameText.isNotBlank(),
                onClick = {
                    chatMultiplatformViewModel.onEvent(ChatMultiplatformEvent.Connect(state.usernameText))
                }
            ) {
                Text(
                    text = "Connect",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}