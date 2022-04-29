package com.kid.playground.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.FragmentActivity
import com.kid.playground.R


class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        requestStoragePermission()

        val wv = findViewById<WebView>(R.id.wv)
        setupWebView(wv)
        wv.loadUrl("http://altwerden-in-vrees.de:8080/Video_Client/frontend/login.xhtml")
        initiateSkypeUri(this, "skype:live:.cid.902d3440bd53874e?call")
    }

    private fun setupWebView(wv: WebView) {
        wv.apply {
                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    loadWithOverviewMode = true
                    mediaPlaybackRequiresUserGesture = false
                    allowContentAccess = true
                    allowFileAccess = true
                    allowFileAccessFromFileURLs = true
                    allowUniversalAccessFromFileURLs = true
                    isNestedScrollingEnabled = true
                }
                webViewClient = BrowserWebViewClient()

//            webChromeClient = BrowserWebChromeClient()

//            setDownloadListener { url, _, _, _, _ ->
//                startActivity(Intent(Intent.ACTION_VIEW).apply {
//                    data = Uri.parse(url)
//                })
//            }

//            if (hostViewModel.isNetworkAvailable) {
//                if (isPdfFile) {
//                    loadUrl("${PDF_VIEWER_URL}${arg.url}")
//                } else {
//                    loadUrl(arg.url)
//                }
//
//            } else {
//                noInternetDialog.show()
//            }
        }
    }

    inner class BrowserWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            val url = request?.url.toString()
            Log.d("chi.trinh","override url: $url")
            if (url.contains("skype")) {
                initiateSkypeUri(this@MainActivity, url)
            } else {
                view?.loadUrl(request?.url.toString())
            }
            return true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            Log.d("chi.trinh","onPageFinished: $url")
        }
    }

    fun initiateSkypeUri(myContext: Context, mySkypeUri: String?) {

        // Make sure the Skype for Android client is installed.
        if (!isSkypeClientInstalled(myContext)) {
            goToMarket(myContext)
            return
        }

        // Create the Intent from our Skype URI.
        val skypeUri = Uri.parse(mySkypeUri)
        val myIntent = Intent(Intent.ACTION_VIEW, skypeUri)

        // Restrict the Intent to being handled by the Skype for Android client only.
//        myIntent.component = ComponentName("com.skype.raider", "com.skype.raider.Main")
        myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        // Initiate the Intent. It should never fail because you've already established the
        // presence of its handler (although there is an extremely minute window where that
        // handler can go away).
        myContext.startActivity(myIntent)
        return
    }

    fun goToMarket(myContext: Context) {
        val marketUri = Uri.parse("market://details?id=com.skype.raider")
        val myIntent = Intent(Intent.ACTION_VIEW, marketUri)
        myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        myContext.startActivity(myIntent)
        return
    }


    fun isSkypeClientInstalled(myContext: Context): Boolean {
        val myPackageMgr = myContext.packageManager
        try {
            myPackageMgr.getPackageInfo("com.skype.raider", 0)
        } catch (e: PackageManager.NameNotFoundException) {
            return false
        }
        return true
    }


}