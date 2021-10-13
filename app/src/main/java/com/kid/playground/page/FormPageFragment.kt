package com.kid.playground.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kid.playground.databinding.FragmentFormPageBinding
import com.kid.playground.form.FormViewModel

class FormPageFragment: Fragment() {

    private val formViewModel: FormViewModel by viewModels ({ requireParentFragment()})

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFormPageBinding.inflate(layoutInflater)

        val pageViewData = formViewModel.pagesLiveData.value!![requireArguments().getInt(
            ARGS_PAGE_POSITION
        )]
        binding.pageLiveData = pageViewData
//        binding.viewmodel = formViewModel
//        binding.position = requireArguments().getInt(ARGS_PAGE_POSITION)
        return binding.root
    }

    companion object {

        const val ARGS_PAGE_POSITION = "ARGS_PAGE_POSITION"

        fun create(position: Int): FormPageFragment {
            val fragment = FormPageFragment()
            fragment.arguments = Bundle(1).apply { putInt(ARGS_PAGE_POSITION, position) }
            return fragment
        }
    }
}