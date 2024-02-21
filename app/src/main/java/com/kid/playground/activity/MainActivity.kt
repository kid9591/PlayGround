package com.kid.playground.activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.kid.playground.R
import java.util.logging.Logger
import java.util.logging.Logger.getLogger


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
//        Thread.currentThread().stackTrace.forEach {
//            if (it.className.contains(this::class.java.simpleName)) {
//                Log.d("chi.trinh", "${it.className}.${it.methodName}()")
//            }
//        }
        Log.d("chi.trinh","Callstack to this function",Throwable())
    }

}