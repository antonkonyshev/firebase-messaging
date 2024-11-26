package com.github.antonkonyshev.firebasemessaging.di

import com.github.antonkonyshev.firebasemessaging.data.MessagingServiceImpl
import com.github.antonkonyshev.firebasemessaging.domain.MessagingService
import org.koin.dsl.module

val networkModule = module {
    single<MessagingService> {
        MessagingServiceImpl()
    }
}