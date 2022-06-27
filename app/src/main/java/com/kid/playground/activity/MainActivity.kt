package com.kid.playground.activity

import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.kid.playground.R
import playwithcustomview.CustomView
import stickerview.StickerView
import stickerview.TextSticker


//https://stackoverflow.com/questions/15091790/how-to-outline-a-textview
//https://www.android-examples.com/add-set-bitmapshader-texture-effect-on-textview-text-in-android/
//https://stackoverflow.com/questions/14791012/android-textured-text

class MainActivity : FragmentActivity() {

    private lateinit var stickerView: StickerView
    private lateinit var textSticker: TextSticker

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val customView = findViewById<CustomView>(R.id.custom_view)
    }
}

