package playwithcustomview

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet


class FontAwesomeTextView : androidx.appcompat.widget.AppCompatTextView {
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context) : super(context) {
        init()
    }

    private fun init() {
        //Font name should not contain "/".
        val tf = Typeface.createFromAsset(
            context.assets,
            "fontawesome470.ttf"
        )
        typeface = tf
    }

    override fun setText(text: CharSequence?, type: BufferType?) {
        val char = FontAwesomeIcons.getUnicodeString(text.toString())
        super.setText(char, type)
    }
}