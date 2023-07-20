package com.kid.playground.activity

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.kid.playground.R

class MainActivity : FragmentActivity()  {

    private lateinit var database: DatabaseReference

    //Some changes here

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val imageview = findViewById<ImageView>(R.id.imageview)
        val url = "https://trello.com/1/cards/64b5097bcf3d6e96468abc98/attachments/64b5097e6e1bcaa5457a768b/download/Kirche.webp"
        val url1 = "https://photos.google.com/photo/AF1QipNy_T94mSBqWGgXUIMXLLE2el8OLhzrfXSIkEpy"
        val fileUrl = filesDir.absolutePath + "/Kirche.webp"
        val filePngUrl = filesDir.absolutePath + "/image.png"
        Glide.with(this)
            .load(fileUrl)
            .into(imageview);
//        imageview.setImageDrawable(getDrawable(R.drawable.kirche))

        //Some changes here


    }
}


