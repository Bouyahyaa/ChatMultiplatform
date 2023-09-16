package com.bouyahya.chatmultiplatform.di

import com.bouyahya.chatmultiplatform.data.ChatMultiplatformRepositoryImpl
import com.bouyahya.chatmultiplatform.domain.repositories.ChatMultiplatformRepository
import com.bouyahya.chatmultiplatform.domain.usecases.ChatMultiplatformUseCase
import com.bouyahya.chatmultiplatform.presentation.ChatMultiplatformViewModel
import io.ktor.client.engine.darwin.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module

actual fun platformModule() = module {
    single {
        Darwin.create()
    }

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

object ViewModels : KoinComponent {
    fun getChatMultiplatformViewModel() = get<ChatMultiplatformViewModel>()
}