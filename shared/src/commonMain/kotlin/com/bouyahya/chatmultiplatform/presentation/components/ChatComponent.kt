package com.bouyahya.chatmultiplatform.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bouyahya.chatmultiplatform.presentation.ChatMultiplatformEvent
import com.bouyahya.chatmultiplatform.presentation.ChatMultiplatformState
import com.bouyahya.chatmultiplatform.presentation.ChatMultiplatformViewModel

@Composable
fun ChatComponent(
    state: ChatMultiplatformState,
    chatMultiplatformViewModel: ChatMultiplatformViewModel,
) {

    val scrollState = rememberScrollState()
    var participantsVisibility by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "ChatMultiplatform",
                style = MaterialTheme.typography.subtitle1.copy(
                    fontSize = 20.sp,
                    color = Color.Black
                )
            )
            if (state.connectedUsers.isNotEmpty())
                IconButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = {
                        participantsVisibility = !participantsVisibility
                    }
                ) {
                    Icon(
                        imageVector = if (participantsVisibility) Icons.Default.Close else Icons.Default.Add,
                        contentDescription = "Participants Visibility"
                    )
                }
        }

        Spacer(modifier = Modifier.size(10.dp))

        AnimatedVisibility(
            visible = participantsVisibility,
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.spacedBy(35.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(state.connectedUsers) { participant ->
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Image(
                                modifier = Modifier.size(50.dp),
                                imageVector = Icons.Default.Person,
                                contentDescription = ""
                            )
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .clip(CircleShape)
                                    .background(color = Color.Green)
                                    .align(Alignment.BottomEnd)
                            )
                        }
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = participant.username,
                            style = MaterialTheme.typography.subtitle1.copy(
                                fontSize = 12.sp,
                                color = Color.Black
                            )
                        )
                    }
                }
            }
        }

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