package com.bouyahya.chatmultiplatform.di

import com.bouyahya.chatmultiplatform.core.network.createHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: (enableLogging: Boolean) -> Module
    get() = { enableLogging ->
        module {
            single { createHttpClient(enableLogging) }
        }
    }