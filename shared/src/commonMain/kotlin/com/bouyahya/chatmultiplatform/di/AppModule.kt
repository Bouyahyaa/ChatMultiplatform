package com.bouyahya.chatmultiplatform.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            networkModule(enableNetworkLogs),
            platformModule()
        )
    }


//Called by iOS
fun initKoin() = initKoin(enableNetworkLogs = true) {}