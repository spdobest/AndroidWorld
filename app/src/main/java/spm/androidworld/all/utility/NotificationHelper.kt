package com.android.assignment.utility

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import spm.androidworld.all.R

@RequiresApi(Build.VERSION_CODES.O)
class NotificationHelper(base: Context?) : ContextWrapper(base) {
    private var notifManager: NotificationManager? = null
    @RequiresApi(Build.VERSION_CODES.O)
    fun createChannels() {
        val notificationChannel = NotificationChannel(
            CHANNEL_ONE_ID,
            CHANNEL_ONE_NAME, NotificationManager.IMPORTANCE_HIGH
        )
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.RED
        notificationChannel.setShowBadge(true)
        notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        manager!!.createNotificationChannel(notificationChannel)
        val notificationChannel2 = NotificationChannel(
            CHANNEL_TWO_ID,
            CHANNEL_TWO_NAME, NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationChannel2.enableLights(false)
        notificationChannel2.enableVibration(true)
        notificationChannel2.lightColor = Color.RED
        notificationChannel2.setShowBadge(false)
        manager!!.createNotificationChannel(notificationChannel2)
    }

    //Create the notification that’ll be posted to Channel One//
    @RequiresApi(Build.VERSION_CODES.O)
    fun getNotification1(
        title: String?,
        body: String?
    ): Notification.Builder {
        return Notification.Builder(
            applicationContext,
            CHANNEL_ONE_ID
        )
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)
    }

    //Create the notification that’ll be posted to Channel Two//
    @RequiresApi(Build.VERSION_CODES.O)
    fun getNotification2(
        title: String?,
        body: String?
    ): Notification.Builder {
        return Notification.Builder(
            applicationContext,
            CHANNEL_TWO_ID
        )
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)
    }

    fun notify(id: Int, notification: Notification.Builder) {
        manager!!.notify(id, notification.build())
    }

    //Send your notifications to the NotificationManager system service//
    private val manager: NotificationManager?
        get() {
            if (notifManager == null) {
                notifManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            }
            return notifManager
        }

    companion object {
        const val CHANNEL_ONE_ID = "com.jessicathornsby.myapplication.ONE"
        const val CHANNEL_ONE_NAME = "Channel One"
        const val CHANNEL_TWO_ID = "com.jessicathornsby.myapplication.TWO"
        const val CHANNEL_TWO_NAME = "Channel Two"
    }

    //Create your notification channels//
    init {
        createChannels()
    }
}