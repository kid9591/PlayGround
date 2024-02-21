package com.kid.playground.activity

import android.app.Activity
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.kid.playground.R
import org.apache.commons.lang3.exception.ExceptionUtils
import java.util.Random
import kotlin.math.sign


class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        fun1()
    }

    private fun fun1() {
        fun2()
    }

    private fun fun2() {
        fun3()
    }

    private fun fun3() {
        Thread.currentThread().stackTrace.forEach {
            if (it.className.contains(this::class.java.simpleName)) {
                Log.d("chi.trinh", "${it.className}.${it.methodName}()")
            }
        }
    }

}