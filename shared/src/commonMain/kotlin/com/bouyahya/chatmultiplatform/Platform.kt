package com.bouyahya.chatmultiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform