package com.kid.playground.activity

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

        val path = "$cacheDir/export_barcode_scan.csv"

        findViewById<Button>(R.id.button).setOnClickListener {
            val reader = CSVReader(FileReader(path), ';')
            val myEntries: List<*> = reader.readAll()
            val pairs = mutableListOf<Pair<String, String>>()
            myEntries.forEach { row ->
                (row as? Array<*>)?.let { columns ->
                    pairs.add(Pair(columns.first().toString(), columns.last().toString()))
                }
            }
            pairs.forEachIndexed { index, it ->
                Log.d("chi.trinh","#$index ${it.first}:${it.second}")
            }
        }
    }
}


