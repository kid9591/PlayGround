package com.kid.playground.activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.kid.playground.R
import com.xiaopo.flying.sticker.*
import kotlin.math.roundToInt


//https://stackoverflow.com/questions/15091790/how-to-outline-a-textview
//https://www.android-examples.com/add-set-bitmapshader-texture-effect-on-textview-text-in-android/
//https://stackoverflow.com/questions/14791012/android-textured-text

class MainActivity : FragmentActivity() {

    private lateinit var stickerView: StickerView

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


//        stickerView = findViewById<StickerView>(R.id.sticker_view)
//
//        stickerView.addSticker(
//            DrawableSticker(getDrawable(R.drawable.sample)),
//            Sticker.Position.CENTER
////            TextSticker(applicationContext)
////                .setText("Sticker")
////                .setMaxTextSize(14f), Sticker.Position.TOP
//        )


        imageView = findViewById(R.id.imageView)
        findViewById<Button>(R.id.button).setOnClickListener {
            test()
        }
    }

    private fun test() {
        val dWidth = imageView.drawable.intrinsicWidth
        val dHeight = imageView.drawable.intrinsicHeight

        val vWidth = imageView.measuredWidth
        val vHeight = imageView.measuredHeight

        imageView.imageMatrix = Matrix().apply {
            //move image to center of imageview
//            setTranslate(
//                ((vWidth - dWidth) * 0.5f),
//                ((vHeight - dHeight) * 0.5f)
//            )

            //flip vertical
//            setScale(-1f,1f, dWidth/2f, 0f)

//            setSkew(0f, 2f, dWidth / 2f, dHeight / 2f)

            //rotate 45 degree clockwise
            setRotate(45f, dWidth / 2f, dHeight / 2f)
        }
    }
}