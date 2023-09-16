package com.bouyahya.chatmultiplatform.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bouyahya.chatmultiplatform.domain.models.MessageData
import kotlin.math.abs

@Composable
fun ChatMultiplatformScreen(
    chatMultiplatformViewModel: ChatMultiplatformViewModel,
) {
    val state by chatMultiplatformViewModel.state.collectAsState()
    val scrollState = rememberScrollState()

    if (state.user != null) {
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
                                        if (message.senderId == state.user!!.id)
                                            Alignment.CenterEnd else
                                            Alignment.CenterStart
                                    ),
                            ) {
                                if (message.senderId == state.user!!.id) {
                                    Text(
                                        modifier = Modifier
                                            .background(
                                                color = if (message.senderId == state.user!!.id)
                                                    Color.Blue.copy(
                                                        alpha = 0.5f
                                                    ) else
                                                    Color.White,
                                                shape = RoundedCornerShape(20.dp)
                                            )
                                            .padding(10.dp),
                                        text = message.message,
                                        color = if (message.senderId == state.user!!.id)
                                            Color.White else
                                            Color.Black
                                    )


                                    Spacer(modifier = Modifier.size(10.dp))

                                    Image(
                                        modifier = Modifier.size(50.dp),
                                        imageVector = Icons.Default.Person,
                                        contentDescription = "profileImage"
                                    )
                                } else {
                                    Image(
                                        modifier = Modifier.size(50.dp),
                                        imageVector = Icons.Default.Person,
                                        contentDescription = "profileImage"
                                    )

                                    Spacer(modifier = Modifier.size(10.dp))

                                    Text(
                                        modifier = Modifier
                                            .background(
                                                color = if (message.senderId == state.user!!.id)
                                                    Color.Blue.copy(
                                                        alpha = 0.5f
                                                    ) else
                                                    Color.White,
                                                shape = RoundedCornerShape(20.dp)
                                            )
                                            .padding(10.dp),
                                        text = message.message,
                                        color = if (message.senderId == state.user!!.id)
                                            Color.White else
                                            Color.Black
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.size(20.dp))
                    }
                }
            }
            TextField(
                modifier = Modifier.fillMaxWidth()
                    .border(
                        border = BorderStroke(
                            0.dp,
                            Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFF7ea1ad),
                                    Color(0xFF151517),
                                ),
                            )
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .weight(1F)
                    .padding(10.dp),
                value = state.messageText,
                onValueChange = {
                    chatMultiplatformViewModel.onEvent(ChatMultiplatformEvent.OnChangeMessageText(it))
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    cursorColor = Color.Black,
                    disabledLabelColor = Color.LightGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
                placeholder = {
                    Text(text = "Type a message...")
                },
                visualTransformation = VisualTransformation.None,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            if (state.messageText.isNotEmpty()) {
                                val messageData = MessageData(
                                    id = abs((0..999999999999).random()),
                                    message = state.messageText,
                                    senderId = state.user!!.id
                                )
                                chatMultiplatformViewModel.onEvent(
                                    ChatMultiplatformEvent.SendMessage(
                                        messageData
                                    )
                                )
                            }
                        }) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Send Message"
                        )
                    }
                }
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
                    enabled = state.usernameText.isNotEmpty(),
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
}