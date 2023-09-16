package com.bouyahya.chatmultiplatform.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class MessageData(
    val id: Long,
    val message: String,
    val senderId: Long,
)