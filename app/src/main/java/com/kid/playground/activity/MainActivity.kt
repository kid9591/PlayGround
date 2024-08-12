package com.kid.playground.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kid.playground.R
import com.kid.playground.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val viewmodel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            this.viewmodel = this@MainActivity.viewmodel
            this.lifecycleOwner = this@MainActivity
            bottomNavigation.itemBackground = StateListDrawable()
                .apply {
                    setExitFadeDuration(200)
//                    alpha = 45
                    addState(
                        intArrayOf(android.R.attr.state_checked),
                        ColorDrawable(Color.RED)
                    )
                    addState(
                        intArrayOf(),
                        ColorDrawable(Color.BLUE)
                    )
                }
        }
    }
}