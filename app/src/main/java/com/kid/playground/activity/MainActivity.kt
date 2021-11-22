package com.kid.playground.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.kid.playground.R
import com.kid.playground.receiver.HeartbeatUtils


class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        HeartbeatUtils.sendFcmBroadcast(this)
        HeartbeatUtils.cancelRequest(this)
        HeartbeatUtils.scheduleHeartbeatRequest(this)
    }
}