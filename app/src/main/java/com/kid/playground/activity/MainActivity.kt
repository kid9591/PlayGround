package com.kid.playground.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.kid.playground.R


//https://stackoverflow.com/questions/15091790/how-to-outline-a-textview
//https://www.android-examples.com/add-set-bitmapshader-texture-effect-on-textview-text-in-android/
//https://stackoverflow.com/questions/14791012/android-textured-text

class MainActivity : FragmentActivity()  {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

    }
}

fun String?.stripHtml(): String? {
    val stripRegex = "\\<.*?\\>".toRegex()
    return this?.replace(stripRegex, "")
}


