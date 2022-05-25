package com.kid.playground.activity

import android.graphics.Matrix
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.kid.playground.R
import com.xiaopo.flying.sticker.*


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


        stickerView = findViewById<StickerView>(R.id.sticker_view)

        val textSticker = TextSticker(applicationContext)
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
    }

    private fun test() {
        val dWidth = imageView.drawable.intrinsicWidth
        val dHeight = imageView.drawable.intrinsicHeight

        val vWidth = imageView.measuredWidth
        val vHeight = imageView.measuredHeight

        imageView.imageMatrix = Matrix().apply {
            //move image to center of imageview
            postTranslate(
                ((vWidth - dWidth) * 0.5f),
                ((vHeight - dHeight) * 0.5f)
            )
            preScale(0.5f, 0.5f, dWidth / 2f, dHeight / 2f)

            //flip vertical
//            setScale(-1f,1f, dWidth/2f, 0f)

//            setSkew(0f, 2f, dWidth / 2f, dHeight / 2f)

            //rotate 45 degree clockwise
//            setRotate(45f, dWidth / 2f, dHeight / 2f)
        }
    }
}