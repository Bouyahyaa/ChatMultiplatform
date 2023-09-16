package com.bouyahya.chatmultiplatform.presentation

import com.bouyahya.chatmultiplatform.domain.models.User

data class ChatMultiplatformState(
    val usernameText: String = "",
    val user: User? = null,
)