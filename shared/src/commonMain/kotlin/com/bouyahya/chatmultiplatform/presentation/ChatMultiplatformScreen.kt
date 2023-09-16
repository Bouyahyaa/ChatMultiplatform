package com.bouyahya.chatmultiplatform.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ChatMultiplatformScreen(
    chatMultiplatformViewModel: ChatMultiplatformViewModel,
) {
    val state by chatMultiplatformViewModel.state.collectAsState()

    if (state.isConnected) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Center).clickable {
                    chatMultiplatformViewModel.onEvent(ChatMultiplatformEvent.SendMessage("Hello!"))
                },
                text = "Chat Multiplatform"
            )
        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = state.username,
                    onValueChange = {
                        chatMultiplatformViewModel.onEvent(
                            ChatMultiplatformEvent.OnChangeUserName(
                                it
                            )
                        )
                    }
                )

                Button(
                    enabled = state.username.isNotEmpty(),
                    onClick = {
                        chatMultiplatformViewModel.onEvent(ChatMultiplatformEvent.Connect(state.username))
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
}