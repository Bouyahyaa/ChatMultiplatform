package com.bouyahya.chatmultiplatform.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bouyahya.chatmultiplatform.domain.models.MessageData
import com.bouyahya.chatmultiplatform.presentation.ChatMultiplatformState

@Composable
fun SenderComponent(
    message: MessageData,
    state: ChatMultiplatformState,
) {
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
        color = if (message.senderId == state.user.id)
            Color.White else
            Color.Black
    )


    Spacer(modifier = Modifier.size(10.dp))

    Image(
        modifier = Modifier.size(50.dp),
        imageVector = Icons.Default.Person,
        contentDescription = "profileImage"
    )
}