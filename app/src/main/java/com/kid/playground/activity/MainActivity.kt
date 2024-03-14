package com.kid.playground.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.ScrollView
import com.kid.playground.R


class MainActivity : Activity() {

    var htmlText = """
        <html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
    <style type='text/css'>
      body {
        display: flex;
        flex-basis: fit-content;
        flex-flow: column wrap;
      }
    </style>
  </head>
  <body>
    <div font-size: 16px;color :#ffe599">
     <p>
        <font style="vertical-align: inherit;">
          <font style="vertical-align: inherit;">With under strikes <input type='text' id='xinput0' value='' onkeyup='AndroidFunction.processInput("xinput0", value)'></font>
        </font>
      </p>
      <p>
        <font style="vertical-align: inherit;">
          <font style="vertical-align: inherit;">
            <p>
              <meta charset="utf-8">Long text
            </p>
            <p>
              <meta charset="utf-8">Long text
            </p>
            <p>
              <meta charset="utf-8">Long text
            </p>
            <p>
              <meta charset="utf-8">Long text
            </p>
            <p>
              <meta charset="utf-8">Long text
            </p>
            <p>
              <meta charset="utf-8">Long text
            </p>
            <p>
              <meta charset="utf-8">Long text
            </p>
            <p>
              <meta charset="utf-8">Long text
            </p>
           
            <br>
          </font>
        </font>
      </p>
       <p>
        <font style="vertical-align: inherit;">
          <font style="vertical-align: inherit;">With under strikes <input type='text' id='xinput0' value='' onkeyup='AndroidFunction.processInput("xinput0", value)'></font>
        </font>
      </p>
       <p>
        <font style="vertical-align: inherit;">
          <font style="vertical-align: inherit;">With under strikes <input type='text' id='xinput0' value='' onkeyup='AndroidFunction.processInput("xinput0", value)'></font>
        </font>
      </p>
      <p>
        <font style="vertical-align: inherit;">
          <font style="vertical-align: inherit;">
            <meta charset="utf-8">
            <p>Long text</p>
            <br>
          </font>
        </font>
      </p>
       <p>
        <font style="vertical-align: inherit;">
          <font style="vertical-align: inherit;">With under strikes <input type='text' id='xinput0' value='' onkeyup='AndroidFunction.processInput("xinput0", value)'></font>
        </font>
      </p>
      <p>
        <font style="vertical-align: inherit;">
          <font style="vertical-align: inherit;">With under strikes <input type='text' id='xinput0' value='' onkeyup='AndroidFunction.processInput("xinput0", value)'></font>
        </font>
      </p>
    </div>
  </body>
</html>
    """.trimIndent()

    private var clickedX: Float = -1f
    private var clickedY: Float = -1f
    
    private var scrollView: ScrollView? = null

    private var scrollViewBottomOrigin: Int? = -1
    private val scrollViewLocation = IntArray(2)

    override fun onResume() {
        super.onResume()

        Handler().postDelayed({
            scrollView!!.getLocationOnScreen(scrollViewLocation)
            scrollViewBottomOrigin = scrollViewLocation[1] + scrollView!!.height
        },1000)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        scrollView = findViewById(R.id.scroll_view)
        val webview = findViewById<WebView>(R.id.webview)


        ObserveKeyboardHeightChanged.assistActivity(this, object: KeyboardHeightChangedListener {
            override fun onChange(keyboardHeight: Int, screenHeight: Int) {
                Log.d("chi.trinh","onChange: $keyboardHeight, screenH: $screenHeight, clickedY: $clickedY")

//                val isScrollViewUpOverClickedY = screenHeight - keyboardHeight < clickedY

                scrollView!!.getLocationOnScreen(scrollViewLocation)
                val scrollViewBottom = scrollViewLocation[1] + scrollView!!.height

                Log.d("chi.trinh","scrollViewBottom $scrollViewBottom")
                Log.d("chi.trinh","scrollViewBottomOrigin $scrollViewBottomOrigin")
                val scrollOffset = Math.abs(scrollViewBottomOrigin!! - scrollViewBottom)
                Log.d("chi.trinh","scrollOffset $scrollOffset")

                val clickedYtoScrollViewBottomOrigin = Math.abs(scrollViewBottomOrigin!!  - clickedY.toInt())

                Log.d("chi.trinh","clickedYtoScrollViewBottomOrigin $clickedYtoScrollViewBottomOrigin")

                val isScrollViewUpOverClickedY = scrollViewBottom < clickedY

                Log.d("chi.trinh","isScrollViewUpOverClickedY $isScrollViewUpOverClickedY")

                val inputHeight = 15
                Handler().postDelayed({
                    if (keyboardHeight > 0) {
                        if (isScrollViewUpOverClickedY) {
                            scrollView!!.scrollBy(0, scrollOffset + inputHeight - clickedYtoScrollViewBottomOrigin)
                        }
                    }
                }, 300)

                clickedY = -1f
            }
        })

        webview.apply {

            setBackgroundColor(Color.TRANSPARENT)

            this.setPadding(0, 0, 0, 0)
            setInitialScale(1)

            setRendererPriorityPolicy(WebView.RENDERER_PRIORITY_BOUND, true)

            settings.apply {
                allowContentAccess = true
                allowFileAccess = true
                allowFileAccessFromFileURLs = true
                allowUniversalAccessFromFileURLs = true
                domStorageEnabled = true
                cacheMode = WebSettings.LOAD_NO_CACHE
                @SuppressLint("SetJavaScriptEnabled")
                javaScriptEnabled = true
                loadWithOverviewMode = true
                useWideViewPort = true
                layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
            }

            loadDataWithBaseURL(null, htmlText, "text/html", "UTF-8", null)

            setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    val x = event.rawX
                    val y = event.rawY

                    // Xử lý vị trí được click tại đây
                    // Ví dụ: Log vị trí được click
                    Log.d("WebView Click", "X: $x, Y: $y")
                    clickedX = x
                    clickedY = y
                }
                false
            }
        }
    }
}