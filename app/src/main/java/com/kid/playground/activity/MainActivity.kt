package com.kid.playground.activity

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import com.kid.playground.R
import com.opencsv.CSVReader
import java.io.FileReader


//https://stackoverflow.com/questions/15091790/how-to-outline-a-textview
//https://www.android-examples.com/add-set-bitmapshader-texture-effect-on-textview-text-in-android/
//https://stackoverflow.com/questions/14791012/android-textured-text

class MainActivity : FragmentActivity()  {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        var title = """
            <div id="defaultStyleBlupassion" style="font-family: Arial, Helvetica, sans-serif; color: rgb(0, 0, 0); font-size: 14px;">
                <p style=""><strong>640 l</strong></p>
                <p style="font-size: 14px;">170 gal</p>
            </div>
        """
        val it = Color.CYAN
        val red: Int = Color.red(it)
        val green: Int = Color.green(it)
        val blue: Int = Color.blue(it)
        val regex = Regex("")
        title =
            title.replace(regex = Regex("color: rgb\\(\\d{1,3}, \\d{1,3}, \\d{1,3}\\)"),"color: rgb($red, $green, $blue)")
        Log.d("chi.trinh", title)
    }
}

fun String?.stripHtml(): String? {
    val stripRegex = "\\<.*?\\>".toRegex()
    return this?.replace(stripRegex, "")
}


