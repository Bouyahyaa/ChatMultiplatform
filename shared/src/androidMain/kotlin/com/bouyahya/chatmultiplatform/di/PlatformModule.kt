package com.bouyahya.chatmultiplatform.di

import com.bouyahya.chatmultiplatform.core.network.ConnectivityStatus
import io.ktor.client.engine.android.Android
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual fun platformModule() = module {

    single {
        Android.create()
    }

    single { ConnectivityStatus(androidContext()) }
}