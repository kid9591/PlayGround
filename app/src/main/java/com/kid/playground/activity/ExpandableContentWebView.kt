package com.kid.playground.activity

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewTreeObserver.OnPreDrawListener
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.view.isVisible
import com.kid.playground.R


/**
 * @author kienht
 * @company OICSoft
 * @since 06/11/2019
 */
class ExpandableContentWebView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var isDescExpanded = false

    init {
        // Inflate layout XML
        LayoutInflater.from(context).inflate(R.layout.expandable_webview_content, this, true)

        // Retrieve custom attribute value from XML
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableContentWebView)
        val htmlText = "<p>Ruhig gelegene Pension, Zimmer ebenerdig, Haustiere willkommen, Familienanschluss, Fahrradweg vor der Haustür<br></p>"//typedArray.getString(R.styleable.ExpandableContentWebView_html)
        typedArray.recycle()

        val relativeShortDesc = findViewById<RelativeLayout>(R.id.relative_short_desc)

        val textShortDesc = findViewById<TermsHtmlView>(R.id.text_short_desc)
        textShortDesc.loadHtml(htmlText)

        val textDesc = findViewById<TermsHtmlView>(R.id.text_desc)
        textDesc.loadHtml(htmlText)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            isDescExpanded = !isDescExpanded

            val buttonText = if (isDescExpanded) "Collapse" else "More"
            button.text = buttonText

            textDesc.isVisible = isDescExpanded
            relativeShortDesc.isVisible = !isDescExpanded
        }

        textDesc.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                view?.loadUrl("javascript:AndroidFunction.resize(document.body.scrollHeight)");
            }
        }
        textDesc.addJavascriptInterface(object {
            @JavascriptInterface
            fun resize(height: Float) {

                Handler(Looper.getMainLooper()).post {
                    val fullHeight = (height * context.resources.displayMetrics.density).toInt()
                    val shortHeight = relativeShortDesc.height

                    Log.d("chi.trinh", "ExpandableContentWebView >> fullHeight : $fullHeight")
                    Log.d("chi.trinh", "ExpandableContentWebView >>  shortHeight : $shortHeight")
                    if (shortHeight < fullHeight) {
                        relativeShortDesc.isVisible = true
                        textDesc.isVisible = false
                    } else {
                        relativeShortDesc.isVisible = false
                        textDesc.isVisible = true
                        button.isVisible = false
                    }
                }
            }
        }, "AndroidFunction")

    }
}