package com.bouyahya.chatmultiplatform.android

import android.app.Application
import com.bouyahya.chatmultiplatform.di.initKoin

class ChatMultiplatformApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}