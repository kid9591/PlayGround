package com.kid.playground.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.kid.playground.receiver.HeartbeatUtils.cancelRequest
import com.kid.playground.receiver.HeartbeatUtils.scheduleHeartbeatRequest
import com.kid.playground.receiver.HeartbeatUtils.sendFcmBroadcast


/**
 * Created by shaobin on 3/8/15.
 */
class HeartbeatReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        sendFcmBroadcast(context)
        cancelRequest(context)
        scheduleHeartbeatRequest(context)

//        Log.d("chi.trinh", "HeartbeatReceiver sent heartbeat request")
//        HeartbeatFixerUtils.scheduleHeartbeatRequest(context, false)
    }
}