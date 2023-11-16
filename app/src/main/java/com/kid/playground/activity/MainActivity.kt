package com.kid.playground.activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import com.kid.playground.R


class MainActivity : Activity() {

    var htmlText = """
        <div id="defaultStyleBlupassion" style="font-family: &quot;Open Sans&quot;; color: rgb(255, 255, 255); font-size: 16px;">
            <p style="font-size: 16px;"><span style="color: rgb(0, 0, 0);"><span style="color: rgb(143, 206, 0);"><strong>alodf</strong></span> df ___</span></p>
            <p style="font-size: 16px;"><span style="color: rgb(0, 0, 0);">đfsf ___ <span style="font-family: SVN-ALoveOfThunder;">sdkfjsdfd</span>&nbsp;</span></p>
            <p style="font-size: 16px;"><span style="color: rgb(0, 0, 0);">dfds ___ fdfkds</span></p>
            <p style="font-size: 16px;"><span style="color: rgb(0, 0, 0);">trung</span></p>
        </div>
    """.trimIndent()

    var htmlWithCss = """
        <html>
           <head>
              <style> 
                 input[type=text] {
                 width: 150px;
                 font-size: 16px;
                 padding: 8px 8px;
                 border: 1px solid #000;
                 border-radius: 5px;
          		 outline: none;
                 }
                 input[type=text]:focus {
                 border: 1px solid #FF0000;
                 }
              </style>
           </head>
           <body>
              %body%
           </body>
        </html>
    """.trimIndent()

    private fun String.replaceInputs(searchStr: String, inputIdPrefix: String): String {
        var newHtmlString = this
        var count = 0

        while (true) {
            val index = newHtmlString.indexOf(searchStr)
            if (index >= 0) {
                newHtmlString = newHtmlString
                    .replaceRange(
                        index,
                        index + searchStr.length,
                        "<input type='text' id='$inputIdPrefix$count' onkeyup='AndroidFunction.processInput(\"$inputIdPrefix$count\", value)'>"
                    )
                count++
            } else {
                break
            }
        }

        return newHtmlString
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val webview = findViewById<WebView>(R.id.webview)

        //setting
        webview.settings.javaScriptEnabled = true
        webview.addJavascriptInterface(WebAppInterface(), "AndroidFunction")

        htmlText = htmlText.replaceInputs("___", "xinput")
        htmlWithCss = htmlWithCss.replace("%body%", htmlText)
        Log.d("chi.trinh", "htmlContainerText: \n$htmlWithCss")

        webview.loadDataWithBaseURL(null, htmlWithCss, "text/html", "UTF-8", null)
    }

    class WebAppInterface {
        @JavascriptInterface
        fun processInput(inputId: String, value: String) {
            Log.d("chi.trinh", "inputId: $inputId, value: $value")
        }
    }
}