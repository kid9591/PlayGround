package com.kid.playground.activity

import android.animation.ValueAnimator
import android.graphics.*
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.kid.playground.R


//https://stackoverflow.com/questions/15091790/how-to-outline-a-textview
//https://www.android-examples.com/add-set-bitmapshader-texture-effect-on-textview-text-in-android/
//https://stackoverflow.com/questions/14791012/android-textured-text

class MainActivity : FragmentActivity(), ValueAnimator.AnimatorUpdateListener {

//    private lateinit var stickerView: StickerView
//    private lateinit var textSticker: TextSticker

    private var ivWidth: Int = 0
    private var ivHeight: Int = 0
    private lateinit var imageView: ImageView

    private lateinit var source: Bitmap
    private lateinit var dest: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val heightScale = 660f / 870
//        val widthScale = 660f / 1229

        imageView = findViewById(R.id.imageview)
        imageView.viewTreeObserver.addOnGlobalLayoutListener {
            ivWidth = imageView.measuredWidth
            ivHeight = imageView.measuredHeight

            source = BitmapFactory.decodeResource(resources, R.mipmap.image_mask)
            val originalResBitmap =
                BitmapFactory.decodeResource(
                    resources,
                    R.mipmap.gaixinh
                )
            //center crop original on background
            val scale = 1f / Math.min(
                originalResBitmap.width.toFloat() / ivWidth,
                originalResBitmap.height.toFloat() / ivHeight
            )
            dest = Bitmap.createScaledBitmap(
                originalResBitmap,
                (originalResBitmap.width * scale).toInt(),
                (originalResBitmap.height * scale).toInt(),
                true
            )

//            startAnimateLogo(0f, 1500f)
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            startAnimateLogo(0f, 1500f)
        }

    }

    private fun draw(yPos: Float) {

        //create empty bitmap with width & height
        val transparentBitmap = Bitmap.createBitmap(ivWidth, ivHeight, Bitmap.Config.ARGB_8888)

        val sourceAdjustCanvas = Canvas(transparentBitmap)
        sourceAdjustCanvas.drawBitmap(source, (ivWidth - source.width) / 2f, yPos, null)


        val resultBitmap = Bitmap.createBitmap(ivWidth, ivHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(resultBitmap)

        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        canvas.drawBitmap(dest, (ivWidth - dest.width) / 2f, (ivHeight - dest.height) / 2f, paint)

        val mode: PorterDuff.Mode = PorterDuff.Mode.DST_IN // choose a mode
        paint.xfermode = PorterDuffXfermode(mode)

        canvas.drawBitmap(transparentBitmap, 0f, 0f, paint)

        imageView.setImageBitmap(resultBitmap)
    }

    private fun startAnimateLogo(fromY: Float, toY: Float) {
        ValueAnimator.ofFloat(fromY, toY).apply {
            duration = 2500
            addUpdateListener(this@MainActivity)
//            addListener(this@MainActivity)
            start()
        }
    }

    override fun onAnimationUpdate(animator: ValueAnimator) {
//        logoAnimatingAngle = animator.animatedValue as? Float ?: -1f
//        invalidate()

        draw(animator.animatedValue as? Float ?: 0f)
    }

}

