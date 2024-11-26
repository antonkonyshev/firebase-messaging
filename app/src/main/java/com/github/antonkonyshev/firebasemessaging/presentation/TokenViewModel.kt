package com.github.antonkonyshev.firebasemessaging.presentation

import androidx.lifecycle.ViewModel
import com.github.antonkonyshev.firebasemessaging.domain.MessagingService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TokenViewModel() : ViewModel(), KoinComponent {
    val messagingService: MessagingService by inject()
}