package com.kid.playground.activity

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View
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
//
//
//        findViewById<Button>(R.id.btnTextSize).setOnClickListener {
//            textSticker.setMaxTextSize(100f)
//            stickerView.invalidate()
//        }
//        findViewById<Button>(R.id.btnTextColor).setOnClickListener {
//            textSticker.setTextColor(resources.getColor(android.R.color.holo_blue_bright,null))
//            stickerView.invalidate()
//        }
//        findViewById<Button>(R.id.btnBorderColor).setOnClickListener {
//            textSticker
//                .setBorderColorEnable(true)
//                .setBorderColor(resources.getColor(android.R.color.white,null))
//            stickerView.invalidate()
//        }
//        findViewById<Button>(R.id.btnBGColor).setOnClickListener {
//            textSticker
//                .setBackgroundColorEnable(true)
//                .setBackgroundColor(resources.getColor(android.R.color.holo_red_dark,null))
//            stickerView.invalidate()
//        }
//        findViewById<Button>(R.id.btnFont).setOnClickListener {
//            textSticker.setTypeface(Typeface.createFromAsset(assets, "waltograph42.ttf"))
//            stickerView.invalidate()
//        }
//        findViewById<Button>(R.id.btnTextAlpha).setOnClickListener {
//            textSticker.setAlpha(100)
//            stickerView.invalidate()
//        }


        val textProgress = findViewById<TextView>(R.id.textProgress)

//        val sampleView = findViewById<SampleView>(R.id.sampleView)
        findViewById<SeekBar>(R.id.seekBar).setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if (fromUser) {
                        val realFloat = progress / 1000f
                        textSticker.setRadius(realFloat)
                        stickerView.invalidate()
                        textProgress.text = realFloat.toString()
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }

            }
        )
    }
}

class SampleView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val mPaint: Paint
    private val originPointPaint: Paint
    private var offset = 0f
    private var radius = -1f
    private val text = "Thley are here!"

    fun setRadius(radius: Float) {
        this.radius = radius
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {

        val textBounds = Rect()
        mPaint.getTextBounds(text, 0 , text.length, textBounds)
        canvas.translate(0f, height/2 - textBounds.height()/2f)

        canvas.drawRect(textBounds, mPaint)
//        canvas.drawText(text, 0f, 0f, mPaint)
        canvas.drawCircle(0f, 0f, 5f, originPointPaint)

        val textWidth: Float = textBounds.width().toFloat()//mPaint.measureText(text)

        //xem ảnh gif: https://stackoverflow.com/questions/48194889/curving-text-in-android
        //radius = 1 -> text cong hết đường tròn -> text nằm nằm nửa trên đường tròn,
        // -1 thì nằm nửa dưới
        //0 thì text nằm ngang trên 1 đường thẳng


        //bán kính nhỏ nhất để vẽ được hết text lên (nhỏ nữa thì bị overlap)
        val minRadius = textWidth / (2 * Math.PI)
        val realRadius = if (radius == 0f) {
            Float.MAX_VALUE
        } else {
            abs(minRadius / radius).toFloat()
        }

        //chu vi
        val circumference = (2 * Math.PI * realRadius).toFloat()

        //góc độ trên đường tròn để vẽ được hết text
        val textAngle = textWidth * 360 / circumference

        //centerAngle = -90 -> text vẽ ở nửa trên đường tròn, centerAngle = 90 -> text vẽ nửa dưới đường tròn
        val centerAngle = if (radius >= 0) {
            -90
        } else {
            90
        }

        //góc bắt đầu vẽ text
        val startAngle: Float = if (radius >= 0) {
            centerAngle - textAngle / 2
        } else {
            centerAngle + textAngle / 2
        }

        //góc quét của vòng cung
        val sweepAngle = if (radius >= 0) {
            textAngle
        } else {
            -textAngle
        }

        //offset text theo chiều dọc
        val vOffset = if (radius >= 0) {
            0f
        } else {
            val fm = mPaint.fontMetrics
            val baseLineToTop = -fm.ascent
            baseLineToTop
        }

        Log.d("chi.trinh", "centerAngle: $centerAngle, textAngle: $textAngle, startAngle: $startAngle, sweepAngle: $sweepAngle, vOffset: $vOffset")
        Log.d("chi.trinh", "textW: $textWidth, realRadius: $realRadius," +
                "realRadiusx2: ${realRadius*2}")

        //make centerX of circle
        val dx = textWidth/2f - realRadius

        val oval = if (radius >= 0) {
            RectF(dx, 0f, dx + realRadius * 2, realRadius * 2)
        } else {
            val fm = mPaint.fontMetrics
            RectF(dx, -abs(fm.ascent) - realRadius * 2, dx + realRadius * 2, -abs(fm.ascent))
        }
        val pathArc = Path()
        pathArc.addArc(oval, startAngle, sweepAngle)

        canvas.drawTextOnPath(text, pathArc, 0f, vOffset, mPaint)


        canvas.drawRect(oval, mPaint)
        canvas.drawPath(pathArc, mPaint)
    }

    init {
        isFocusable = true
        val textSize = 90f
        mPaint = TextPaint().apply {
            isAntiAlias = true
            style = Paint.Style.STROKE
            this.textSize = textSize
            color = Color.RED
        }
        originPointPaint = Paint().apply {
            isAntiAlias = true
            style = Paint.Style.FILL
            color = Color.BLUE
        }
    }
}

