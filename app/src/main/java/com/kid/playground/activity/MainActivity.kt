package com.kid.playground.activity

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.kid.playground.R
import com.kid.playground.form.FormFragment


class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val latlng = UTMConverter.convertToLatLng(400226.5463368808, 5579520.506886347, 32, 'U')
        Log.d("chi.trinh", "pair: $latlng")
    }
}