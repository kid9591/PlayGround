package com.kid.playground.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.kid.playground.R

class ParkingBgView(context: Context, attrs: AttributeSet) : View(context, attrs){

    private var lineWidth: Float

    private var strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
    }

    private var fillPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.RED
    }

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ParkingBgView,
            0, 0).apply {

            try {
                lineWidth = getDimensionPixelSize(R.styleable.ParkingBgView_strokeWidth, 0) * 1f

                val paintColor = getColor(R.styleable.ParkingBgView_lineColor, Color.RED)
                strokePaint.apply {
                    strokeWidth = lineWidth
                    color = paintColor
                }

                val fillColor = getColor(R.styleable.ParkingBgView_fillColor, Color.GRAY)
                fillPaint.apply {
                    strokeWidth = lineWidth
                    color = fillColor
                }
            } finally {
                recycle()
            }
        }
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.apply {
            val height = height.toFloat() - lineWidth
            val width = width.toFloat() - lineWidth

            val leftXDraw = 0f + lineWidth/2
            val rightXDraw = this@ParkingBgView.width - lineWidth/2
            val topYDraw = 0f + lineWidth/2
            val bottomYDraw = this@ParkingBgView.height - lineWidth/2

            val freeSpaceTextWidth = resources.getDimensionPixelSize(R.dimen._150dp) * 1f

            val radiusLeftArc = height / 2

            val radiusRightArc = height / 2
            val left = width - freeSpaceTextWidth
            val top = bottomYDraw - radiusRightArc
            val right = left + radiusRightArc * 2
            val bottom = height + radiusRightArc
            val ovalRect = RectF(left, top, right, bottom)

            //draw bottom right gray part
            drawArc(ovalRect.apply { offset(lineWidth/2, lineWidth/2) }, 180f, 90f, true, fillPaint)
            drawRect(RectF(ovalRect.centerX(), ovalRect.top, this@ParkingBgView.width.toFloat(), ovalRect.bottom), fillPaint)

            //draw left arc
            drawArc(RectF(leftXDraw,topYDraw,radiusLeftArc * 2f, bottomYDraw), 90f, 180f, false, strokePaint)

            //draw top line
            drawLine(radiusLeftArc, topYDraw, rightXDraw, topYDraw, strokePaint)

            //draw bottom line
            drawLine(radiusLeftArc, bottomYDraw, rightXDraw - freeSpaceTextWidth, bottomYDraw, strokePaint)

            //draw free space arc
            drawArc(ovalRect.apply { offset(-lineWidth/2, -lineWidth/2)}, 180f, 90f, false, strokePaint)


            //draw center line
            drawLine(left + radiusRightArc, bottomYDraw - radiusRightArc, rightXDraw, bottomYDraw - radiusRightArc, strokePaint)

            //draw right line
            drawLine(rightXDraw, 0f, rightXDraw, 0f + this@ParkingBgView.height.toFloat()/2 + lineWidth/2, strokePaint)
        }
    }
}