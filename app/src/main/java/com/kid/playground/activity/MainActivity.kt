package com.kid.playground.activity

import android.content.res.Resources
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.kid.playground.R
import com.nex3z.flowlayout.FlowLayout

class MainActivity : FragmentActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val flowLayout = findViewById<FlowLayout>(R.id.flowLayout)

        flowLayout.addView(TextView(this).apply {
            text = "Text view 1"
        })
        flowLayout.addView(EditText(this).apply {
            hint = "Edit text 1"
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
            maxLines = 1
            ellipsize = TextUtils.TruncateAt.END
            width = 100.px
        })
        flowLayout.addView(TextView(this).apply {
            text = "Text view 2"
        })
        flowLayout.addView(EditText(this).apply {
            hint = "Edit text 2"
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
            maxLines = 1
            ellipsize = TextUtils.TruncateAt.END
            width = 120.px
        })
        flowLayout.addView(TextView(this).apply {
            text = "Text view 3"
        })
        flowLayout.addView(EditText(this).apply {
            hint = "Edit text 3"
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
            maxLines = 1
            ellipsize = TextUtils.TruncateAt.END
            width = 130.px
        })
        flowLayout.addView(TextView(this).apply {
            text = "Text view 4"
        })
        flowLayout.addView(EditText(this).apply {
            hint = "Edit text 4"
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
            maxLines = 1
            ellipsize = TextUtils.TruncateAt.END
            width = 140.px
        })

    }
}

val Int.dp: Int get() = (this * 1.0 / Resources.getSystem().displayMetrics.density).toInt()
val Int.px: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()



