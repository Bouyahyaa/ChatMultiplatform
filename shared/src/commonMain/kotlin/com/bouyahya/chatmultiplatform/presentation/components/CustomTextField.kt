package com.bouyahya.chatmultiplatform.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.bouyahya.chatmultiplatform.presentation.ChatMultiplatformState

@Composable
fun CustomTextField(
    modifier: Modifier,
    state: ChatMultiplatformState,
    onValueChange: (String) -> Unit,
    onTrailingIconClick: () -> Unit,
) {
    TextField(
        modifier = modifier.fillMaxWidth()
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
            ).padding(10.dp),
        value = state.messageText,
        onValueChange = {
            onValueChange(it)
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
                    onTrailingIconClick()
                }) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send Message"
                )
            }
        }
    )
}