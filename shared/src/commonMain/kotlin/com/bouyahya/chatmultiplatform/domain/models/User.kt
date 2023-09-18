package com.bouyahya.chatmultiplatform.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long,
    val username: String,
)