package com.kid.playground.activity

import android.app.Application
import android.graphics.Typeface
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    fun setTypeface(typeface: Typeface) {
        _fontLiveData.value = typeface
    }
    val fontLiveData: LiveData<Typeface>
    get() = _fontLiveData
    private val _fontLiveData = MutableLiveData(Typeface.DEFAULT)

    fun setFontSize(size: Int) {
        _fontSizeLiveData.postValue(size)
    }
    val fontSizeLiveData: LiveData<Int>
        get() = _fontSizeLiveData
    private val _fontSizeLiveData = MutableLiveData(50)

    fun setText(text: String) {
        _textLiveData.postValue(text)
    }
    val textLiveData: LiveData<String>
        get() = _textLiveData
    private val _textLiveData = MutableLiveData("Some")

}
