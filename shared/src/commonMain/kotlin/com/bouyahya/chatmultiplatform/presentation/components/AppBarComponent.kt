package com.bouyahya.chatmultiplatform.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.bouyahya.chatmultiplatform.domain.models.User

@Composable
fun AppBarComponent(
    participantsVisibility: Boolean,
    connectedUsers: List<User>,
    onTrailingIconClick: () -> Unit,
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
        if (connectedUsers.isNotEmpty())
            IconButton(
                modifier = Modifier.align(Alignment.CenterEnd),
                onClick = {
                    onTrailingIconClick()
                }
            ) {
                Icon(
                    imageVector = if (participantsVisibility) Icons.Default.Close else Icons.Default.Add,
                    contentDescription = "Participants Visibility"
                )
            }
    }
}