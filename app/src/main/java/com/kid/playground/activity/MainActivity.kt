package com.kid.playground.activity

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import com.kid.playground.R


class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val dropdownItems = listOf("a", "b", "c")

        findViewById<AutoCompleteTextView>(R.id.dropdown_text_view).apply {
            setAdapter(
                ColorableDropDownAdapter(
                    Color.YELLOW,
                    context,
                    android.R.layout.simple_spinner_dropdown_item,
                    dropdownItems
                )
            )
            setDropDownBackgroundDrawable(ColorDrawable(Color.GREEN))
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
        val view =  super.getView(position, convertView, parent)
        val textview = view.findViewById<TextView>(android.R.id.text1)
        textview.setTextColor(textColor)
        return view
    }
}