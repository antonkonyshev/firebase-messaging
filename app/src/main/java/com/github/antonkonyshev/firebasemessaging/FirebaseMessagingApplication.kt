package com.github.antonkonyshev.firebasemessaging

import android.app.Application
import com.github.antonkonyshev.firebasemessaging.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class FirebaseMessagingApplication : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@FirebaseMessagingApplication)
            modules(networkModule)
        }
    }
}