package com.bouyahya.chatmultiplatform.di

import com.bouyahya.chatmultiplatform.core.network.ConnectivityStatus
import com.bouyahya.chatmultiplatform.data.ChatMultiplatformRepositoryImpl
import com.bouyahya.chatmultiplatform.domain.repositories.ChatMultiplatformRepository
import com.bouyahya.chatmultiplatform.domain.usecases.ChatMultiplatformUseCase
import com.bouyahya.chatmultiplatform.presentation.ChatMultiplatformViewModel
import io.ktor.client.engine.okhttp.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual fun platformModule() = module {

    single {
        OkHttp.create()
    }

    single { ConnectivityStatus(androidContext()) }

    single<ChatMultiplatformRepository> {
        ChatMultiplatformRepositoryImpl(
            httpClient = get()
        )
    }

    single {
        ChatMultiplatformUseCase(chatMultiplatformRepository = get())
    }

    single {
        ChatMultiplatformViewModel(
            chatMultiplatformUseCase = get()
        )
    }
}