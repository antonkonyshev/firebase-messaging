package com.github.antonkonyshev.firebasemessaging

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.github.antonkonyshev.firebasemessaging.presentation.TokenScreen
import com.github.antonkonyshev.firebasemessaging.ui.theme.FirebaseMessagingTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (ActivityCompat.checkSelfPermission(
                applicationContext, Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val notificaitonPermissionRequest = registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) {}
            notificaitonPermissionRequest.launch(Manifest.permission.POST_NOTIFICATIONS)
        }

        enableEdgeToEdge()
        setContent {
            FirebaseMessagingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TokenScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
