package com.kid.playground.activity

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.kid.playground.R
import stickerview.Sticker
import stickerview.StickerView
import stickerview.TextSticker
import kotlin.math.abs


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

//        requestStoragePermission()

//        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.grass)
//        val tv = SuperTextView(this, -0xff7f01, 10f, bitmap)
//        tv.setBackgroundColor(resources.getColor(android.R.color.darker_gray))
//        tv.setShadowLayer(10f, 0f, 0f, Color.GRAY)
//        tv.text = "Simple test"
//        tv.textSize = 40f


        stickerView = findViewById<StickerView>(R.id.sticker_view)

        textSticker = TextSticker(applicationContext)
            .setText("They are Sticker Sticker Sticker")
            .setMaxTextSize(24f)

        stickerView.addSticker(
//            DrawableSticker(getDrawable(R.drawable.sample)),
//            Sticker.Position.CENTER
            textSticker,
            Sticker.Position.TOP
        )


        findViewById<Button>(R.id.btnTextSize).setOnClickListener {
            textSticker.setMaxTextSize(100f)
            stickerView.invalidate()
        }
        findViewById<Button>(R.id.btnTextColor).setOnClickListener {
            textSticker.setTextColor(resources.getColor(android.R.color.holo_blue_bright,null))
            stickerView.invalidate()
        }
        findViewById<Button>(R.id.btnBorderColor).setOnClickListener {
            textSticker
                .setBorderColorEnable(true)
                .setBorderColor(resources.getColor(android.R.color.white,null))
            stickerView.invalidate()
        }
        findViewById<Button>(R.id.btnBGColor).setOnClickListener {
            textSticker
                .setBackgroundColorEnable(true)
                .setBackgroundColor(resources.getColor(android.R.color.holo_red_dark,null))
            stickerView.invalidate()
        }
        findViewById<Button>(R.id.btnFont).setOnClickListener {
            textSticker.setTypeface(Typeface.createFromAsset(assets, "waltograph42.ttf"))
            stickerView.invalidate()
        }
        findViewById<Button>(R.id.btnTextAlpha).setOnClickListener {
            textSticker.setAlpha(100)
            stickerView.invalidate()
        }

//        val textProgress = findViewById<TextView>(R.id.textProgress)

//        val sampleView = findViewById<SampleView>(R.id.sampleView)
//        findViewById<SeekBar>(R.id.seekBar).setOnSeekBarChangeListener(
//            object : SeekBar.OnSeekBarChangeListener {
//                override fun onProgressChanged(
//                    seekBar: SeekBar?,
//                    progress: Int,
//                    fromUser: Boolean
//                ) {
//                    if (fromUser) {
//                        val realFloat = progress / 1000f
//                        textSticker.setRadius(realFloat)
//                        stickerView.invalidate()
//                        textProgress.text = realFloat.toString()
//                    }
//                }
//
//                override fun onStartTrackingTouch(seekBar: SeekBar?) {
//                }
//
//                override fun onStopTrackingTouch(seekBar: SeekBar?) {
//                }
//
//            }
//        )
    }
}

