package com.movie.android.presentation

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.movie.android.R
import kotlin.random.Random

private const val CHANNEL_ID = "channel"

class FireBaseService : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)


        Log.d("Firebase", message.notification!!.body!!)
        val bundle = Bundle()
        bundle.putInt("movieId", message.notification!!.body!!.toInt())

        Log.d("Firebase", message.notification!!.body!!)

        createNotification(bundle, message)

    }

    private fun createNotification(bundle: Bundle, message: RemoteMessage) {
        val intent = Intent(this, MainActivity::class.java)
        val notificationID = Random.nextInt(1, 10000)

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = NavDeepLinkBuilder(this)
            .setComponentName(MainActivity::class.java)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.detailsFragment)
            .setArguments(bundle)
            .createPendingIntent()

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(message.notification!!.title)
            .setContentText("Hey!Come check out the new Movie")
            .setSmallIcon(R.drawable.ic_baseline_crop_original_24)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel(notificationManager)
        }

        notificationManager.notify(notificationID, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel(notificationManager: NotificationManager) {
        val channelName = "FirebaseChannel"
        val channel = NotificationChannel(CHANNEL_ID, channelName, IMPORTANCE_HIGH).apply {
            description = "ChannelDesc"
            enableLights(true)
        }
        notificationManager.createNotificationChannel(channel)

    }
}