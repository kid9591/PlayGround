package com.kid.playground.activity

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kid.playground.R
import com.kid.playground.databinding.ActivityMainBinding
import java.io.File


class MainActivity : AppCompatActivity() {

    private val viewmodel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            this.viewmodel = this@MainActivity.viewmodel
            this.lifecycleOwner = this@MainActivity

            button.setOnClickListener {
                val fontFile = File(filesDir, "impact.ttf")
                val typeface = Typeface.createFromFile(fontFile)
                this@MainActivity.viewmodel.setTypeface(typeface)
                this@MainActivity.viewmodel.setFontSize(140)
                this@MainActivity.viewmodel.setText("abc")
            }
        }
    }
}

class ColorableDropDownAdapter(
    private val textColor: Int,
    context: Context,
    itemRes: Int,
    items: List<String>
) : ArrayAdapter<String>(context, itemRes, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        val textview = view.findViewById<TextView>(android.R.id.text1)
        textview.setTextColor(textColor)
        return view
    }
}