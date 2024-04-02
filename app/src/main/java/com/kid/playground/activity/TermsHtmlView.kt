package com.kid.playground.activity

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.webkit.*
import androidx.databinding.BindingAdapter

/**
 * @author kienht
 * @company OICSoft
 * @since 06/11/2019
 */
class TermsHtmlView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : WebView(context, attrs, defStyleAttr) {

    private var html: String? = null

    var listener: TermsShouldOverrideUrlLoadingListener? = null

    interface TermsShouldOverrideUrlLoadingListener {
        fun shouldOverrideUrlLoading(url: String?)
    }

    init {
        setBackgroundColor(Color.TRANSPARENT)
        isNestedScrollingEnabled = true
        isVerticalScrollBarEnabled = false
        isHorizontalScrollBarEnabled = false

        this.setPadding(0, 0, 0, 0)
        setInitialScale(1)

        setRendererPriorityPolicy(RENDERER_PRIORITY_BOUND, true)

        settings.apply {
            cacheMode = WebSettings.LOAD_NO_CACHE
            @SuppressLint("SetJavaScriptEnabled")
            javaScriptEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
            layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
        }

        webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                listener?.shouldOverrideUrlLoading(request?.url?.toString())
                return true
            }
        }
    }

    fun loadHtml(html: String?) {
        if (!html.isNullOrEmpty() && !this.html.equals(html, true)) {
            this.html = html
            val data = "<html>" +
                    "<head>" +
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no\"/>" +
                    "<style type='text/css'>" +
                    "body { display: flex; flex-basis: fit-content; flex-flow: column wrap; }" +
                    "* { margin: 0em; }" +
                    "h1,p,h1,h2,h3,h4,h5,h6,blockquote {font-weight: normal;}" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "$html" +
                    "</body>" +
                    "</html>"
            loadDataWithBaseURL(null, data, "text/html", "UTF-8", null)
        }
    }
}