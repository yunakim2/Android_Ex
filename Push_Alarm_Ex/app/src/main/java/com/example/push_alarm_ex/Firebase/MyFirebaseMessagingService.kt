package com.example.push_alarm_ex.Firebase

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.push_alarm_ex.MainActivity
import com.example.push_alarm_ex.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        if (p0.notification != null) {
            sendNotification(p0.notification?.title,p0.notification!!.body!!)

        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("firebase", "firebaseInstanceID : $token")
    }

    private fun sendNotification(title: String?, body: String) {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0,intent, PendingIntent.FLAG_ONE_SHOT)
        val defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notiBuilder: NotificationCompat.Builder
        val notiManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "sopt"
            val name = "SOPTReminderChannel"
            val description = "Channel for SOPT Reminder"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(
                channelId,
                name,
                importance
            )
            channel.description = description
            notiManager.createNotificationChannel(channel)
            notiBuilder = NotificationCompat.Builder(this, channelId)
        } else {
            notiBuilder = NotificationCompat.Builder(this)
        }
        notiBuilder.setSmallIcon (R.mipmap.ic_launcher)
            .setContentTitle(title).setContentText(body).setSound(defaultSound)
            .setContentIntent(pendingIntent)
        notiManager.notify(0, notiBuilder.build())
    }
}

