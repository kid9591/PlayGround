package com.kid.playground.page

import androidx.lifecycle.MutableLiveData
import com.kid.playground.model.InputViewData

class PageLiveData(inputViewData: InputViewData): MutableLiveData<InputViewData>(inputViewData)