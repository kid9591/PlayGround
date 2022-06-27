package playwithcustomview

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.graphics.toRectF
import com.kid.playground.R

class CustomView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val arcPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        strokeWidth = 5f
        style = Paint.Style.STROKE
    }

    private val textPaint = TextPaint(TextPaint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.FILL
        textSize = 63f
        typeface = Typeface.createFromAsset(
            context.assets,
            "fontawesome470.ttf"
        )
    }

    private val iconSize = 63f

    private val bitmap = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.sample,
        null
    )

//    private val bitmap = Bitmap.createScaledBitmap(originalBitmap, 63, (63.0/originalBitmap.width * originalBitmap.height).toInt(), true)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val min = widthMeasureSpec.coerceAtMost(heightMeasureSpec)
        setMeasuredDimension(min, min)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val oval = Rect(width / 4, height / 4, 3 * width / 4, 3 * height / 4)
        val sweepAngle = 90f
        val startAngle = 0f

        val pathArc = Path()
        pathArc.addArc(oval.toRectF(), startAngle, sweepAngle)

        canvas.drawPath(pathArc, arcPaint)

        val pathMeasure = PathMeasure(pathArc, false)
        val numberOfIcons = 4

        val iconMargin = (5 * Resources.getSystem().displayMetrics.density).toInt()
        val iconWidth = /*bitmap.width*/iconSize
        val lengthForIcons = numberOfIcons * iconWidth + (numberOfIcons - 1) * iconMargin
        val startLength = (pathMeasure.length - lengthForIcons) / 2 + iconWidth / 2
        val stepLength = iconWidth + iconMargin
        Log.d(
            "chi.trinh",
            "iconMargin: $iconMargin, iconWidth: $iconWidth, iconsLength: $lengthForIcons, totalLength: ${pathMeasure.length}, start: $startLength, step: $stepLength"
        )

        for (i in 0 until numberOfIcons) {
            val distance = startLength + i * stepLength
//            val distance = i * pathMeasure.length / numberOfIcons
            val matrix = Matrix()
            pathMeasure.getMatrix(
                distance,
                matrix,
                PathMeasure.POSITION_MATRIX_FLAG + PathMeasure.TANGENT_MATRIX_FLAG
            )
            matrix.preScale(iconSize / bitmap.width, iconSize / bitmap.width)
            matrix.preTranslate(-bitmap.width.toFloat()/2, -bitmap.width.toFloat()/2)

//            canvas.drawBitmap(bitmap, matrix, null)
            canvas.save()
            canvas.concat(matrix)
//            if (i % 2 == 0) {
                canvas.drawBitmap(bitmap, 0f, 0f, null)
//            } else {
//                val text = "fa-address-card"
//                setTextSizeForHeight(tex
//                tPaint, bitmap.height.toFloat(), text)
//                val vOffset = with(textPaint.fontMetrics) {
//                    - ascent
//                }
//                canvas.drawText(
//                    FontAwesomeIcons.getUnicodeString("fa-address-card"),
//                    -bitmap.width.toFloat()/2,
//                    vOffset,
//                    textPaint
//                )
//            }
            canvas.restore()
        }

//        canvas.drawRect(oval, arcPaint)
    }

    /**
     * Sets the text size for a Paint object so a given string of text will be a
     * given width.
     *
     * @param paint
     * the Paint to set the text size for
     * @param desiredHeight
     * the desired width
     * @param text
     * the text that should be that width
     */
    private fun setTextSizeForHeight(
        paint: Paint, desiredHeight: Float,
        text: String
    ) {

        // Pick a reasonably large value for the test. Larger values produce
        // more accurate results, but may cause problems with hardware
        // acceleration. But there are workarounds for that, too; refer to
        // http://stackoverflow.com/questions/6253528/font-size-too-large-to-fit-in-cache
        val testTextSize = 200f

        // Get the bounds of the text, using our testTextSize.
        paint.textSize = testTextSize
        val bounds = Rect()
        paint.getTextBounds(text, 0, text.length, bounds)

        // Calculate the desired size as a proportion of our testTextSize.
        val desiredTextSize = testTextSize * desiredHeight / bounds.height()

        // Set the paint for that size.
        paint.textSize = desiredTextSize
    }

}