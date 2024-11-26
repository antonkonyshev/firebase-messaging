package com.github.antonkonyshev.firebasemessaging.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.antonkonyshev.firebasemessaging.R
import com.github.antonkonyshev.firebasemessaging.ui.theme.FirebaseMessagingTheme
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

@Composable
fun TokenScreen(viewModel: TokenViewModel = viewModel(), modifier: Modifier = Modifier) {
    var token by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (task.isSuccessful) {
                token = task.result
            }
        })
    }

    TokenPlaceholder(token = token)
}

@Composable
fun TokenPlaceholder(token: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = stringResource(R.string.firebase_messaging_token))
            Text(text = token, modifier = Modifier.padding(vertical = 10.dp))
            TextField(value = token, onValueChange = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TokenPlaceholderPreview() {
    FirebaseMessagingTheme {
        TokenPlaceholder("test-test")
    }
}
