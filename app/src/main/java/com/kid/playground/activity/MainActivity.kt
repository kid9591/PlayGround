package com.kid.playground.activity

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.webkit.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import com.kid.playground.R
import com.kid.playground.form.FormFragment


class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webViewContent = findViewById(R.id.web_view_content)

        setupWebView()

        webViewContent.loadUrl("https://clickdoc.elvi.de")
    }

    lateinit var webViewContent: WebView

    private fun setupWebView() {
        webViewContent.apply {
            settings.allowContentAccess = true
            settings.allowFileAccess = true
            settings.allowFileAccessFromFileURLs = true
            settings.allowUniversalAccessFromFileURLs = true
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.loadWithOverviewMode = true
            settings.mediaPlaybackRequiresUserGesture = false
            webViewClient = BrowserWebViewClient()
            webChromeClient = BrowserWebChromClient()
        }
    }

    inner class BrowserWebChromClient : WebChromeClient() {
        override fun onPermissionRequest(request: PermissionRequest?) {
//            super.onPermissionRequest(request)
            Log.d("chi.trinh", "request resources: ${request?.resources}")

            cameraAndAudioPermissions.launch(
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.CAPTURE_AUDIO_OUTPUT
                )
            )
            request?.grant(request.resources)
        }
    }

    inner class BrowserWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            view?.loadUrl(request?.url.toString())
            Log.d("chi.trinh","loading url: ${request?.url.toString()}")
            return true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
        }
    }

    private val cameraAndAudioPermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
//            if (result.isNotEmpty() && result.values.all { it }) {
//                Log.d("chi.trinh","accept all perms")
//                webViewContent.loadUrl("https://clickdoc.elvi.de")
//            }
        }

//    private fun askPermissionIfNeeded() {
//        when {
//            shouldShowRationale(
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) -> {
//                MaterialDialog(requireContext()).show {
//                    message(res = de.ikssoftware.blupassion.core.R.string.core_permission_message_for_location_on_map)
//                    positiveButton {
//                        locationPermissions.launch(
//                            arrayOf(
//                                Manifest.permission.ACCESS_FINE_LOCATION,
//                                Manifest.permission.ACCESS_COARSE_LOCATION
//                            )
//                        )
//                    }
//                    negativeButton(res = de.ikssoftware.blupassion.core.R.string.cancel)
//                }
//            }
//            else -> {
//                locationPermissions.launch(
//                    arrayOf(
//                        Manifest.permission.ACCESS_FINE_LOCATION,
//                        Manifest.permission.ACCESS_COARSE_LOCATION
//                    )
//                )
//            }
//        }
//    }
}