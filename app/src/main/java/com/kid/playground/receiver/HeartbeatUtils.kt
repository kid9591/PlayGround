package com.kid.playground.receiver

import android.app.AlarmManager
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.kid.playground.R

object HeartbeatUtils {
    fun cancelRequest(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val broadcastPendingIntent: PendingIntent =
            PendingIntent.getBroadcast(
                context,
                0,
                Intent(context, HeartbeatReceiver::class.java),
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT // setting the mutability flag
            )
        alarmManager.cancel(broadcastPendingIntent)
    }

    fun scheduleHeartbeatRequest(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val triggerAtMillis: Long = System.currentTimeMillis() + 1 * 60 * 1000
        val broadcastPendingIntent: PendingIntent =
            PendingIntent.getBroadcast(
                context,
                0,
                Intent(context, HeartbeatReceiver::class.java),
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT // setting the mutability flag
            )
        val rtcWakeup = AlarmManager.RTC_WAKEUP
        alarmManager.setExact(rtcWakeup, triggerAtMillis, broadcastPendingIntent)
    }
}