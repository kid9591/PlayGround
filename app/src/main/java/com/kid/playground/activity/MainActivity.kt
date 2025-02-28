package com.kid.playground.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.itextpdf.html2pdf.html.AttributeConstants.i
import com.kid.playground.R
import java.util.*


class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        handleDeepLink(intent)
    }

    private fun handleDeepLink(intent: Intent) {
        val data = intent.data
        if (data != null) {
            val deepLinkUrl = data.toString()
            Toast.makeText(this, "Deep Link nhận được: $deepLinkUrl", Toast.LENGTH_LONG)
        }
    }
}