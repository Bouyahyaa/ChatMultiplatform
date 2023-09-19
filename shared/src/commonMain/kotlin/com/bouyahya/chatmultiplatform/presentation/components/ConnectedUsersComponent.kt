package com.bouyahya.chatmultiplatform.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bouyahya.chatmultiplatform.domain.models.User

@Composable
fun ConnectedUsersComponent(
    participantsVisibility: Boolean,
    connectedUsers: List<User>,
) {
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
            items(connectedUsers) { participant ->
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
}