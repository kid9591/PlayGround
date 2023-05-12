package com.kid.playground1.widget

import android.content.Context
import android.graphics.*
import androidx.appcompat.widget.AppCompatTextView

class SuperTextView(
    context: Context,
    var borderColor: Int, //relative to font size
    var borderWidth: Float,
    texture: Bitmap?
) : AppCompatTextView(context) {
    private var shader: Shader? = null

    override fun onDraw(canvas: Canvas) {
        //create paint for outline
        val paint: Paint = paint
        paint.shader = null
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth
        val textColor = paint.color
        setTextColor(borderColor)
        //draw outline
        super.onDraw(canvas)

        //restore paint
        paint.shader = shader
        setTextColor(textColor)
        paint.style = Paint.Style.FILL
        //draw standard text
        super.onDraw(canvas)
    }

    init {
        if (texture != null) {
            shader = BitmapShader(texture, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
        }
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }
}