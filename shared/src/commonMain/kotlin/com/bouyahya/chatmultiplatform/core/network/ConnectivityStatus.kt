package com.bouyahya.chatmultiplatform.core.network

import kotlinx.coroutines.flow.MutableStateFlow

expect class ConnectivityStatus(appContext: Any? = null) {
    val isNetworkConnected: MutableStateFlow<Boolean>
    fun start()
    fun stop()
    fun getStatus(success: (Boolean) -> Unit)
}