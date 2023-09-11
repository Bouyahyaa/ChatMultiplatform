package com.bouyahya.chatmultiplatform.di

import com.bouyahya.chatmultiplatform.core.network.ConnectivityStatus
import io.ktor.client.engine.darwin.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module

actual fun platformModule() = module {
    single {
        Darwin.create()
    }

    //single or factory can be used to get a view-model object for swiftui
    single {
        ConnectivityStatus(null)
    }
}


object SharedUtils : KoinComponent {
    fun getConnectivityStatus() = get<ConnectivityStatus>()
}