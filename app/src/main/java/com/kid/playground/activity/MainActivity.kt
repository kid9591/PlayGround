package com.kid.playground.activity

import android.graphics.*
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import java.util.Collections.min


//https://stackoverflow.com/questions/15091790/how-to-outline-a-textview
//https://www.android-examples.com/add-set-bitmapshader-texture-effect-on-textview-text-in-android/
//https://stackoverflow.com/questions/14791012/android-textured-text

class MainActivity : FragmentActivity() {

//    private lateinit var stickerView: StickerView
//    private lateinit var textSticker: TextSticker

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.kid.playground.R.layout.activity_main)

//        val heightScale = 660f / 870
//        val widthScale = 660f / 1229

        val imageView = findViewById<ImageView>(com.kid.playground.R.id.imageview)

        val mask = BitmapFactory.decodeResource(resources, com.kid.playground.R.mipmap.image_mask)

        val originalResBitmap =
            BitmapFactory.decodeResource(
                resources,
                com.kid.playground.R.mipmap.original1
            )

        //center crop original on mask
        val scale = 1f / Math.min(originalResBitmap.width.toFloat()/mask.width, originalResBitmap.height.toFloat()/mask.height )

        Log.d("chi.trinh","scale: $scale")

        val original = Bitmap.createScaledBitmap(
            originalResBitmap, (originalResBitmap.width * scale).toInt(), (originalResBitmap.height * scale).toInt(), true
        )

        if (original != null) {
            //create empty bitmap with width & height
            val resultBitmap = Bitmap.createBitmap(mask.width, mask.height, Bitmap.Config.ARGB_8888)

            val canvas = Canvas(resultBitmap)

            val paint = Paint(Paint.ANTI_ALIAS_FLAG)
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)

            canvas.drawBitmap(original, (mask.width - original.width)/2f, (mask.height - original.height)/2f, null)
            canvas.drawBitmap(mask, 0f, 0f, paint)

            paint.xfermode = null
            paint.style = Paint.Style.STROKE


            imageView.setImageBitmap(resultBitmap)
        }
    }
}

