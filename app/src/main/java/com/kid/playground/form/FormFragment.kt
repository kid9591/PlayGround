package com.kid.playground.form

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kid.playground.page.FormPageFragment
import com.kid.playground.databinding.FragmentFormBinding

class FormFragment: Fragment() {

    private val formViewModel: FormViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        formViewModel.fakePages()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFormBinding.inflate(layoutInflater)
        binding.button.setOnClickListener {
            for (inputViewData in formViewModel.collectPagesData) {
                Log.d("chi.trinh", inputViewData.text)
            }
        }

        binding.viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return FormPageFragment.create(position)
            }

            override fun getItemCount(): Int {
                return formViewModel.pagesLiveData.value!!.size
            }
        }

        return binding.root
    }

}