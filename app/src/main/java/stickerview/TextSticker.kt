package stickerview

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.Log
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import androidx.annotation.IntRange
import androidx.core.content.ContextCompat
import androidx.core.graphics.toRect
import com.kid.playground.R
import kotlin.math.*

/**
 * Customize your sticker with text and image background.
 * You can place some text into a given region, however,
 * you can also add a plain text sticker. To support text
 * auto resizing , I take most of the code from AutoResizeTextView.
 * See https://adilatwork.blogspot.com/2014/08/android-textview-which-resizes-its-text.html
 * Notice: It's not efficient to add long text due to too much of
 * StaticLayout object allocation.
 * Created by liutao on 30/11/2016.
 */
class TextSticker @JvmOverloads constructor(
    private val context: Context,
    drawable: Drawable? = null
) : Sticker() {
    private var textPadding = 0
    private val realBounds: Rect
    private val textRect: Rect
    private val textPaint: TextPaint
    private val textBorderPaint: TextPaint
    private val backgroundPaint: Paint
    private val originPointPaint: Paint
    private var drawable: Drawable
    private var alignment: Layout.Alignment
    private var text: String? = ""
    private var radiusScale = 0f

    /**
     * Upper bounds for text size.
     * This acts as a starting point for resizing.
     */
    private var maxTextSizePixels: Float
    /**
     * @return lower text size limit, in pixels.
     */
    /**
     * Lower bounds for text size.
     */
    var minTextSizePixels: Float
        private set

    /**
     * Line spacing multiplier.
     */
    private var lineSpacingMultiplier = 1.0f

    /**
     * Additional line spacing.
     */
    private var lineSpacingExtra = 0.0f

    /**
     * Enable background color
     */
    private var backgroundEnable = false

    /**
     * Enable border color
     */
    private var borderEnable = false
    override fun draw(canvas: Canvas) {
        val matrix = matrix
//        canvas.save()
//        canvas.concat(matrix)
//        if (drawable != null) {
//                drawable!!.bounds = realBounds
//                drawable!!.draw(canvas)
//        }
//        canvas.restore()

//        canvas.save()
//        canvas.concat(matrix)
//        val dx = textPadding
//        val dy = height / 2f - textBounds.exactCenterY()
//        canvas.translate(dx.toFloat(), dy)
//        if (backgroundEnable) {
//            val textBounds = textBounds
//            val backgroundBounds = Rect(
//                textBounds.left - textPadding,
//                textBounds.top - textPadding,
//                textBounds.right + textPadding,
//                textBounds.bottom + textPadding
//            )
//            canvas.drawRect(backgroundBounds, backgroundPaint)
//        }
//        if (borderEnable) {
//            canvas.drawText(text!!, 0f, 0f, textBorderPaint)
//        }
//        canvas.drawText(text!!, 0f, 0f, textPaint)
//        canvas.drawCircle(0f, 0f, 5f, originPointPaint)
//        canvas.restore()


        canvas.save()
        canvas.concat(matrix)

        val dx = textPadding
        val dy =
            abs(textPaint.fontMetrics.ascent) + textPadding //height / 2f - textBounds.exactCenterY()
        canvas.translate(dx.toFloat(), dy)
//        canvas.drawRect(textBounds, textPaint)

        canvas.drawCircle(0f, 0f, 5f, originPointPaint)

        val textWidth: Float = textBounds.width().toFloat()//mPaint.measureText(text)

        //xem ảnh gif: https://stackoverflow.com/questions/48194889/curving-text-in-android
        //radius = 1 -> text cong hết đường tròn -> text nằm nằm nửa trên đường tròn,
        // -1 thì nằm nửa dưới

        //0 thì text nằm ngang trên 1 đường thẳng
        //bán kính nhỏ nhất để vẽ được hết text lên (nhỏ nữa thì bị overlap)
        val minRadius = textWidth / (2 * Math.PI)
        val radius = if (radiusScale == 0f) {
            Float.MAX_VALUE
        } else {
            abs(minRadius / radiusScale).toFloat()
        }

        //chu vi
        val circumference = (2 * Math.PI * radius).toFloat()

        //góc độ trên đường tròn để vẽ được hết text
        val textAngle = textWidth * 360 / circumference

        //centerAngle = -90 -> text vẽ ở nửa trên đường tròn, centerAngle = 90 -> text vẽ nửa dưới đường tròn
        val centerAngle = if (radiusScale >= 0) {
            -90
        } else {
            90
        }

        //góc bắt đầu vẽ text
        val startAngle: Float = if (radiusScale >= 0) {
            centerAngle - textAngle / 2
        } else {
            centerAngle + textAngle / 2
        }

        //góc quét của vòng cung
        val sweepAngle = if (radiusScale >= 0) {
            textAngle
        } else {
            -textAngle
        }

        //offset text theo chiều dọc
        val vOffset = if (radiusScale >= 0) {
            0f
        } else {
            val fm = textPaint.fontMetrics
            val baseLineToTop = -fm.ascent
            baseLineToTop
        }

        Log.d(
            "chi.trinh",
            "centerAngle: $centerAngle, textAngle: $textAngle, startAngle: $startAngle, sweepAngle: $sweepAngle, vOffset: $vOffset"
        )
        Log.d(
            "chi.trinh", "textW: $textWidth, realRadius: $radius," +
                    "realRadiusx2: ${radius * 2}"
        )

        //make centerX of circle
        val circleDx = textWidth / 2f - radius

        val oval = if (radiusScale >= 0) {
            RectF(circleDx, 0f, circleDx + radius * 2, radius * 2)
        } else {
            val fm = textPaint.fontMetrics
            RectF(circleDx, -abs(fm.ascent) - radius * 2, circleDx + radius * 2, -abs(fm.ascent))
        }
        val pathArc = Path()
        pathArc.addArc(oval, startAngle, sweepAngle)

        canvas.drawTextOnPath(text!!, pathArc, 0f, vOffset, textPaint)

//        canvas.drawRect(oval, textPaint)
//        canvas.drawPath(pathArc, textPaint)

        //xu ly oval's bounds khac text's bounds
        val oldTop = oval.top
        val oldBottom = oval.bottom
        val oldCenterX = oval.centerX()
        val oldLeft = oval.left
        val oldRight = oval.right
        val fontAscent = abs(textPaint.fontMetrics.ascent)
        val radiusContainText = radius + fontAscent

        if (textAngle < 180) {
            //tinh dx tu diem cuoi cua curve text ben trai den diem noi tam hinh tron va chinh giua cua text. tinh bang cong thuc canh goc vuong = canh huyen x sin goc doi (canh huyen la radius, goc doi la 1/2 x textAngle)
            val leftTextPointToCenterHorizontal =
                abs(radiusContainText * sin((textAngle / 2) * PI / 180)).toFloat() // * PI/180 to convert from degree to radian
            //tinh dy tu diem top cua curve circle xuong den điểm cắt của leftTextPoint giao với centerHorizontal (canh goc vuong = canh huyen x cos goc ke)
            val topTextPointDownToTextBottomPoint =
                radius - abs(radius * cos((textAngle / 2) * PI / 180)).toFloat()

            if (radiusScale > 0) {
                oval.apply {
                    top -= (abs(textPaint.fontMetrics.ascent) + textPadding)
                    left = oldCenterX - leftTextPointToCenterHorizontal - textPadding
                    right = oldCenterX + leftTextPointToCenterHorizontal + textPadding
                    bottom = oldTop + topTextPointDownToTextBottomPoint + textPadding
                }
            } else {
                oval.apply {
                    top = oldBottom - topTextPointDownToTextBottomPoint - textPadding
                    left = oldCenterX - leftTextPointToCenterHorizontal - textPadding
                    right = oldCenterX + leftTextPointToCenterHorizontal + textPadding
                    bottom += (abs(textPaint.fontMetrics.ascent) + textPadding)
                }
            }
        } else {
            //tinh dy tu tam hinh tron den điểm cắt của leftTextPoint giao với centerHorizontal (canh goc vuong = canh huyen x cos goc ke)
            val circleCenterToTextBottomPoint =
                abs(radiusContainText * cos((180 - textAngle / 2) * PI / 180)).toFloat()

            if (radiusScale > 0) {
                oval.apply {
                    top -= (abs(textPaint.fontMetrics.ascent) + textPadding)
                    left = oldLeft - fontAscent - textPadding
                    right = oldRight + fontAscent + textPadding
                    bottom = oldTop + radius + circleCenterToTextBottomPoint + textPadding
                }
            } else {
                oval.apply {
                    top = oldBottom - radius - circleCenterToTextBottomPoint - textPadding
                    left = oldLeft - fontAscent - textPadding
                    right = oldRight + fontAscent + textPadding
                    bottom += (abs(textPaint.fontMetrics.ascent) + textPadding)
                }
            }
        }
        if (radiusScale != 0F) {
            canvas.drawRect(oval, textPaint)
            realBounds.set(oval.toRect())
        } else {
            realBounds.set(textBounds.apply {
                left -= textPadding
                top -= textPadding
                right += textPadding
                bottom += textPadding
            })
            canvas.drawRect(
                realBounds, textPaint
            )
        }

        canvas.restore()
    }

    private fun calculateRealBounds(): Rect {
        //0 thì text nằm ngang trên 1 đường thẳng
        //bán kính nhỏ nhất để vẽ được hết text lên (nhỏ nữa thì bị overlap)
        val minRadius = textWidth / (2 * Math.PI)
        val radius = if (radiusScale == 0f) {
            Float.MAX_VALUE
        } else {
            abs(minRadius / radiusScale).toFloat()
        }

        //chu vi
        val circumference = (2 * Math.PI * radius).toFloat()

        //góc độ trên đường tròn để vẽ được hết text
        val textAngle = textWidth * 360 / circumference

        //offset text theo chiều dọc
        val vOffset = if (radiusScale >= 0) {
            0f
        } else {
            val fm = textPaint.fontMetrics
            val baseLineToTop = -fm.ascent
            baseLineToTop
        }

        Log.d(
            "chi.trinh", "textW: $textWidth, realRadius: $radius," +
                    "realRadiusx2: ${radius * 2}"
        )

        //make centerX of circle
        val circleDx = textWidth / 2f - radius

        val oval = if (radiusScale >= 0) {
            RectF(circleDx, 0f, circleDx + radius * 2, radius * 2)
        } else {
            val fm = textPaint.fontMetrics
            RectF(circleDx, -abs(fm.ascent) - radius * 2, circleDx + radius * 2, -abs(fm.ascent))
        }

        //xu ly oval's bounds khac text's bounds
        val oldTop = oval.top
        val oldBottom = oval.bottom
        val oldCenterX = oval.centerX()
        val oldLeft = oval.left
        val oldRight = oval.right
        val fontAscent = abs(textPaint.fontMetrics.ascent)
        val radiusContainText = radius + fontAscent

        if (textAngle < 180) {
            //tinh dx tu diem cuoi cua curve text ben trai den diem noi tam hinh tron va chinh giua cua text. tinh bang cong thuc canh goc vuong = canh huyen x sin goc doi (canh huyen la radius, goc doi la 1/2 x textAngle)
            val leftTextPointToCenterHorizontal =
                abs(radiusContainText * sin((textAngle / 2) * PI / 180)).toFloat() // * PI/180 to convert from degree to radian
            //tinh dy tu diem top cua curve circle xuong den điểm cắt của leftTextPoint giao với centerHorizontal (canh goc vuong = canh huyen x cos goc ke)
            val topTextPointDownToTextBottomPoint =
                radius - abs(radius * cos((textAngle / 2) * PI / 180)).toFloat()

            if (radiusScale > 0) {
                oval.apply {
                    top -= (abs(textPaint.fontMetrics.ascent) + textPadding)
                    left = oldCenterX - leftTextPointToCenterHorizontal - textPadding
                    right = oldCenterX + leftTextPointToCenterHorizontal + textPadding
                    bottom = oldTop + topTextPointDownToTextBottomPoint + textPadding
                }
            } else {
                oval.apply {
                    top = oldBottom - topTextPointDownToTextBottomPoint - textPadding
                    left = oldCenterX - leftTextPointToCenterHorizontal - textPadding
                    right = oldCenterX + leftTextPointToCenterHorizontal + textPadding
                    bottom += (abs(textPaint.fontMetrics.ascent) + textPadding)
                }
            }
        } else {
            //tinh dy tu tam hinh tron den điểm cắt của leftTextPoint giao với centerHorizontal (canh goc vuong = canh huyen x cos goc ke)
            val circleCenterToTextBottomPoint =
                abs(radiusContainText * cos((180 - textAngle / 2) * PI / 180)).toFloat()

            if (radiusScale > 0) {
                oval.apply {
                    top -= (abs(textPaint.fontMetrics.ascent) + textPadding)
                    left = oldLeft - fontAscent - textPadding
                    right = oldRight + fontAscent + textPadding
                    bottom = oldTop + radius + circleCenterToTextBottomPoint + textPadding
                }
            } else {
                oval.apply {
                    top = oldBottom - radius - circleCenterToTextBottomPoint - textPadding
                    left = oldLeft - fontAscent - textPadding
                    right = oldRight + fontAscent + textPadding
                    bottom += (abs(textPaint.fontMetrics.ascent) + textPadding)
                }
            }
        }
        return if (radiusScale != 0F) {
            oval.toRect()
        } else {
            textBounds.apply {
                left -= textPadding
                top -= textPadding
                right += textPadding
                bottom += textPadding
            }
        }
    }

    override fun getWidth(): Int {
//            return drawable.getIntrinsicWidth();
        return textWidth + 2 * textPadding
    }

    override fun getHeight(): Int {
            return drawable.getIntrinsicHeight();
        return textHeight + 2 * textPadding
    }

    private val textHeight: Int
        get() {
            val bounds = Rect()
            textPaint.getTextBounds(text, 0, text!!.length, bounds)
            return bounds.height()
        }
    private val textWidth: Int
        get() {
            val bounds = Rect()
            textPaint.getTextBounds(text, 0, text!!.length, bounds)
            return bounds.width()
        }
    private val textBounds: Rect
        get() {
            val bounds = Rect()
            textPaint.getTextBounds(text, 0, text!!.length, bounds)
            return bounds
        }

    override fun release() {
        super.release()
//        if (drawable != null) {
//            drawable = null
//        }
    }

    override fun setAlpha(@IntRange(from = 0, to = 255) alpha: Int): TextSticker {
        textPaint.alpha = alpha
        textBorderPaint.alpha = alpha
        backgroundPaint.alpha = alpha
        return this
    }

    override fun getDrawable(): Drawable {
        return drawable
    }

    override fun setDrawable(drawable: Drawable): TextSticker {
        this.drawable = drawable
        realBounds[0, 0, width] = height
        textRect[0, 0, width] = height
        return this
    }

    fun setDrawable(drawable: Drawable, region: Rect?): TextSticker {
        this.drawable = drawable
        realBounds[0, 0, width] = height
        if (region == null) {
            textRect[0, 0, width] = height
        } else {
            textRect[region.left, region.top, region.right] = region.bottom
        }
        return this
    }

    fun setTypeface(typeface: Typeface?): TextSticker {
        textPaint.typeface = typeface
        textBorderPaint.typeface = typeface
        return this
    }

    fun setTextColor(@ColorInt color: Int): TextSticker {
        val currentAlpha = textPaint.alpha
        textPaint.color = color
        textPaint.alpha = currentAlpha
        return this
    }

    fun setTextAlign(alignment: Layout.Alignment): TextSticker {
        this.alignment = alignment
        return this
    }

    fun setMaxTextSize(@Dimension(unit = Dimension.SP) size: Float): TextSticker {
        textPaint.textSize = convertSpToPx(size)
        textBorderPaint.textSize = convertSpToPx(size)
        maxTextSizePixels = textPaint.textSize
        return this
    }

    fun setBackgroundColorEnable(enable: Boolean): TextSticker {
        backgroundEnable = enable
        return this
    }

    fun setBackgroundColor(@ColorInt color: Int): TextSticker {
        val currentAlpha = backgroundPaint.alpha
        backgroundPaint.color = color
        backgroundPaint.alpha = currentAlpha
        return this
    }

    fun setBorderColorEnable(enable: Boolean): TextSticker {
        borderEnable = enable
        return this
    }

    fun setBorderColor(@ColorInt color: Int): TextSticker {
        val currentAlpha = textBorderPaint.alpha
        textBorderPaint.color = color
        textBorderPaint.alpha = currentAlpha
        return this
    }

    /**
     * Sets the lower text size limit
     *
     * @param minTextSizeScaledPixels the minimum size to use for text in this view,
     * in scaled pixels.
     */
    fun setMinTextSize(minTextSizeScaledPixels: Float): TextSticker {
        minTextSizePixels = convertSpToPx(minTextSizeScaledPixels)
        return this
    }

    fun setLineSpacing(add: Float, multiplier: Float): TextSticker {
        lineSpacingMultiplier = multiplier
        lineSpacingExtra = add
        return this
    }

    fun setText(text: String?): TextSticker {
        this.text = text
        return this
    }

    fun setRadius(radius: Float): TextSticker {
        this.radiusScale = radius
        return this
    }

    /**
     * Resize this view's text size with respect to its width and height
     * (minus padding). You should always call this method after the initialization.
     */
    /*fun resizeText(): TextSticker {
        val availableHeightPixels = textRect.height()
        val availableWidthPixels = textRect.width()
        val text: CharSequence? = text

        // Safety check
        // (Do not resize if the view does not have dimensions or if there is no text)
        if (text == null || text.length <= 0 || availableHeightPixels <= 0 || availableWidthPixels <= 0 || maxTextSizePixels <= 0) {
            return this
        }
        var targetTextSizePixels = maxTextSizePixels
        var targetTextHeightPixels =
            getTextHeightPixels(text, availableWidthPixels, targetTextSizePixels)

        // Until we either fit within our TextView
        // or we have reached our minimum text size,
        // incrementally try smaller sizes
        while (targetTextHeightPixels > availableHeightPixels
            && targetTextSizePixels > minTextSizePixels
        ) {
            targetTextSizePixels = Math.max(targetTextSizePixels - 2, minTextSizePixels)
            targetTextHeightPixels =
                getTextHeightPixels(text, availableWidthPixels, targetTextSizePixels)
        }

        // If we have reached our minimum text size and the text still doesn't fit,
        // append an ellipsis
        // (NOTE: Auto-ellipsize doesn't work hence why we have to do it here)
        if (targetTextSizePixels == minTextSizePixels
            && targetTextHeightPixels > availableHeightPixels
        ) {
            // Make a copy of the original TextPaint object for measuring
            val textPaintCopy = TextPaint(textPaint)
            textPaintCopy.textSize = targetTextSizePixels

            // Measure using a StaticLayout instance
            val staticLayout = StaticLayout(
                text, textPaintCopy, availableWidthPixels, Layout.Alignment.ALIGN_NORMAL,
                lineSpacingMultiplier, lineSpacingExtra, false
            )

            // Check that we have a least one line of rendered text
            if (staticLayout.lineCount > 0) {
                // Since the line at the specific vertical position would be cut off,
                // we must trim up to the previous line and add an ellipsis
                val lastLine = staticLayout.getLineForVertical(availableHeightPixels) - 1
                if (lastLine >= 0) {
                    val startOffset = staticLayout.getLineStart(lastLine)
                    var endOffset = staticLayout.getLineEnd(lastLine)
                    var lineWidthPixels = staticLayout.getLineWidth(lastLine)
                    val ellipseWidth = textPaintCopy.measureText(mEllipsis)

                    // Trim characters off until we have enough room to draw the ellipsis
                    while (availableWidthPixels < lineWidthPixels + ellipseWidth) {
                        endOffset--
                        lineWidthPixels = textPaintCopy.measureText(
                            text.subSequence(startOffset, endOffset + 1).toString()
                        )
                    }
                    setText(text.subSequence(0, endOffset).toString() + mEllipsis)
                }
            }
        }
        textPaint.textSize = targetTextSizePixels
        staticLayout = StaticLayout(
            this.text, textPaint, textRect.width(), alignment, lineSpacingMultiplier,
            lineSpacingExtra, true
        )
        return this
    }*/

    /**
     * Sets the text size of a clone of the view's [TextPaint] object
     * and uses a [StaticLayout] instance to measure the height of the text.
     *
     * @return the height of the text when placed in a view
     * with the specified width
     * and when the text has the specified size.
     */
    /*protected fun getTextHeightPixels(
        source: CharSequence, availableWidthPixels: Int,
        textSizePixels: Float
    ): Int {
        textPaint.textSize = textSizePixels
        // It's not efficient to create a StaticLayout instance
        // every time when measuring, we can use StaticLayout.Builder
        // since api 23.
        val staticLayout = StaticLayout(
            source, textPaint, availableWidthPixels, Layout.Alignment.ALIGN_NORMAL,
            lineSpacingMultiplier, lineSpacingExtra, true
        )
        return staticLayout.height
    }*/

    /**
     * @return the number of pixels which scaledPixels corresponds to on the device.
     */
    private fun convertSpToPx(scaledPixels: Float): Float {
        return scaledPixels * context.resources.displayMetrics.scaledDensity
    }

    companion object {
        /**
         * Our ellipsis string.
         */
        private const val mEllipsis = "\u2026"
    }

    init {
        this.drawable = drawable ?: ContextCompat.getDrawable(
            context,
            R.drawable.sticker_transparent_background
        )!!
        textPadding = (8 * context.resources.displayMetrics.density).roundToInt()
        textPaint = TextPaint(TextPaint.ANTI_ALIAS_FLAG)
        textPaint.style = Paint.Style.STROKE
        textPaint.color = Color.RED
        textBorderPaint = TextPaint(TextPaint.ANTI_ALIAS_FLAG)
        textBorderPaint.style = Paint.Style.STROKE
        textBorderPaint.strokeWidth = convertSpToPx(2f)
        backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        originPointPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        originPointPaint.color = Color.BLUE
        realBounds = Rect(0, 0, 100, 100)
        textRect = Rect(0, 0, width, height)
        minTextSizePixels = convertSpToPx(6f)
        maxTextSizePixels = convertSpToPx(32f)
        alignment = Layout.Alignment.ALIGN_CENTER
        textPaint.textSize = maxTextSizePixels
        textBorderPaint.textSize = maxTextSizePixels
    }
}