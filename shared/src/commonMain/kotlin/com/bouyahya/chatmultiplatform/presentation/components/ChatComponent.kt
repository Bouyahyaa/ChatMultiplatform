package com.bouyahya.chatmultiplatform.presentation.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bouyahya.chatmultiplatform.presentation.ChatMultiplatformEvent
import com.bouyahya.chatmultiplatform.presentation.ChatMultiplatformState
import com.bouyahya.chatmultiplatform.presentation.ChatMultiplatformViewModel

@Composable
fun ChatComponent(
    state: ChatMultiplatformState,
    chatMultiplatformViewModel: ChatMultiplatformViewModel,
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .weight(10F)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .verticalScroll(
                        state = scrollState,
                        reverseScrolling = true
                    )
                    .align(Alignment.BottomCenter),
            ) {
                for (message in state.messages) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier
                                .align(
                                    if (message.senderId == state.currentUser!!.id)
                                        Alignment.CenterEnd else
                                        Alignment.CenterStart
                                ),
                        ) {
                            if (message.senderId == state.currentUser.id) {
                                SenderComponent(
                                    message = message,
                                    state = state
                                )
                            } else {
                                ReceiverComponent(
                                    message = message,
                                    state = state
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.size(20.dp))
                }
            }
        }

        CustomTextField(
            modifier = Modifier.weight(1F),
            state = state,
            onValueChange = {
                chatMultiplatformViewModel.onEvent(ChatMultiplatformEvent.OnChangeMessageText(it))
            },
            onTrailingIconClick = {
                if (state.messageText.isNotEmpty()) {
                    chatMultiplatformViewModel.onEvent(
                        ChatMultiplatformEvent.SendMessage(
                            state.messageText
                        )
                    )
                }
            }
        )
    }
}