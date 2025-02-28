package com.kid.playground.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.kid.playground.R
import android.net.Uri


//https://stackoverflow.com/questions/15091790/how-to-outline-a-textview
//https://www.android-examples.com/add-set-bitmapshader-texture-effect-on-textview-text-in-android/
//https://stackoverflow.com/questions/14791012/android-textured-text

class NewActivity : FragmentActivity()  {
    //Some changes here
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        //Some changes here
        // ATTENTION: This was auto-generated to handle app links.
        val appLinkIntent: Intent = intent
        val appLinkAction: String? = appLinkIntent.action
        val appLinkData: Uri? = appLinkIntent.data

        Log.d("chi.trinh", "appLinkAction : $appLinkAction")
        Log.d("chi.trinh", "appLinkData : ${appLinkData.toString()}")

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Toast.makeText(this, "New Activity new intent", Toast.LENGTH_LONG).show()
        Log.d("chi.trinh", "new intent data: ${intent!!.data}")
    }

}

