package com.kid.playground.activity

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import com.kid.playground.R
import com.opencsv.CSVReader
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.io.FileReader


//https://stackoverflow.com/questions/15091790/how-to-outline-a-textview
//https://www.android-examples.com/add-set-bitmapshader-texture-effect-on-textview-text-in-android/
//https://stackoverflow.com/questions/14791012/android-textured-text

class MainActivity : FragmentActivity()  {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            val bPass = BCryptPasswordEncoder(10).encode("auRu4IdaeNg9")
            Log.d("chi.trinh", bPass)
        }
    }
}

fun String?.stripHtml(): String? {
    val stripRegex = "\\<.*?\\>".toRegex()
    return this?.replace(stripRegex, "")
}


