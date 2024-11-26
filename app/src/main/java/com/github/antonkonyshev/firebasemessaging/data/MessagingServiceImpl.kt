package com.github.antonkonyshev.firebasemessaging.data

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.github.antonkonyshev.firebasemessaging.R
import com.github.antonkonyshev.firebasemessaging.domain.MessagingService
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessagingServiceImpl() : FirebaseMessagingService(), MessagingService {
    private var currentNotificaitonId: Int = 0

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        if (message.notification != null) {
            showNotification(message.notification!!)
        }
    }

    private fun showNotification(notification: RemoteMessage.Notification) {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel(notificationManager)
        }
        notificationManager.notify(
            currentNotificaitonId++,
            NotificationCompat.Builder(applicationContext, notificationChannelId)
                .setContentTitle(notification.title)
                .setContentText(notification.body)
                .setSmallIcon(R.mipmap.ic_launcher)
                .build()
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel(notificationManager: NotificationManager) {
        if (notificationManager.getNotificationChannel(notificationChannelId) == null) {
            notificationManager.createNotificationChannel(
                NotificationChannel(
                    notificationChannelId,
                    getString(R.string.firebase_messaging),
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            )
        }
    }

    companion object {
        const val TAG = "FirebaseMessagingService"
        const val notificationChannelId = "FirebaseMessagingService"
    }
}