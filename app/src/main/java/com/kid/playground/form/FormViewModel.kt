package com.kid.playground.form

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kid.playground.model.InputViewData
import com.kid.playground.page.PageLiveData

class FormViewModel: ViewModel() {
    val pagesLiveData = MutableLiveData<MutableList<PageLiveData>>(mutableListOf())

    val formNameLiveData = MutableLiveData("Form name")

    fun fakePages() {
        for (i in 0..20) {
            pagesLiveData.value?.add(PageLiveData(InputViewData("Hint for $i")))
        }
    }

    val collectPagesData: List<InputViewData>
    get() {
        return pagesLiveData.value!!.map { it.value!!}
    }
}

