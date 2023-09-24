package com.bouyahya.chatmultiplatform.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bouyahya.chatmultiplatform.domain.models.MessageData
import com.bouyahya.chatmultiplatform.presentation.ChatMultiplatformState
import com.seiko.imageloader.rememberImagePainter

@Composable
fun SenderComponent(
    message: MessageData,
    state: ChatMultiplatformState,
) {
    Text(
        modifier = Modifier
            .background(
                color = if (message.senderId == state.currentUser!!.id)
                    Color.Blue.copy(
                        alpha = 0.5f
                    ) else
                    Color.White,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(10.dp),
        text = message.message,
        color = if (message.senderId == state.currentUser.id)
            Color.White else
            Color.Black
    )


    Spacer(modifier = Modifier.size(10.dp))

    Image(
        modifier = Modifier.size(50.dp).clip(CircleShape),
        painter = rememberImagePainter(state.currentUser.profileImage),
        contentDescription = "profile Image",
        contentScale = ContentScale.Crop
    )
}