package com.kid.playground.receiver

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.kid.playground.receiver.HeartbeatUtils.cancelRequest
import com.kid.playground.receiver.HeartbeatUtils.scheduleHeartbeatRequest
import androidx.core.app.NotificationCompat

import android.R
import android.provider.Settings
import androidx.core.app.NotificationManagerCompat
import com.kid.playground.receiver.HeartbeatUtils.showTestNoti


/**
 * Created by shaobin on 3/8/15.
 */
class HeartbeatReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        context.sendBroadcast(GTALK_HEART_BEAT_INTENT)
        context.sendBroadcast(MCS_MCS_HEARTBEAT_INTENT)
        Log.d("chi.trinh","send heartbeat")

        showTestNoti(context)
        cancelRequest(context)
        scheduleHeartbeatRequest(context)

//        Log.d("chi.trinh", "HeartbeatReceiver sent heartbeat request")
//        HeartbeatFixerUtils.scheduleHeartbeatRequest(context, false)
    }



    companion object {
        private val GTALK_HEART_BEAT_INTENT: Intent =
            Intent("com.google.android.intent.action.GTALK_HEARTBEAT")
        private val MCS_MCS_HEARTBEAT_INTENT: Intent =
            Intent("com.google.android.intent.action.MCS_HEARTBEAT")
    }
}