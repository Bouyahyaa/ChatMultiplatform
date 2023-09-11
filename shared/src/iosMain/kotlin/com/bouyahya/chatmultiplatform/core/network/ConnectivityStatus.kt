package com.bouyahya.chatmultiplatform.core.network

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import platform.Network.*
import platform.darwin.*
import kotlin.native.concurrent.freeze

actual class ConnectivityStatus actual constructor(appContext: Any?) {
    actual val isNetworkConnected = MutableStateFlow(false)
    private val monitor = nw_path_monitor_create()

    init {
        // Freeze the monitor to ensure thread-safety
        monitor.freeze()
    }

    actual fun start() {
        nw_path_monitor_set_queue(monitor, dispatch_get_main_queue())

        // Set the path update handler
        nw_path_monitor_set_update_handler(monitor) { path ->

            // Check the network status and update isNetworkConnected accordingly
            val isConnected = when (nw_path_get_status(path)) {
                nw_path_status_satisfied,
                nw_path_status_satisfiable,
                -> true
                else -> false
            }
            isNetworkConnected.value = isConnected
        }

        // Start monitoring
        nw_path_monitor_start(monitor)
    }

    actual fun stop() {
        // Cancel monitoring
        nw_path_monitor_cancel(monitor)
    }

    actual fun getStatus(success: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.Default).launch {
            isNetworkConnected.collect { status ->
                withContext(Dispatchers.Main) {
                    success(status)
                }
            }
        }
    }
}