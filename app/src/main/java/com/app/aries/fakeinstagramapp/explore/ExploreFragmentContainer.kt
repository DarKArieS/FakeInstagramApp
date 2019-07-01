package com.app.aries.fakeinstagramapp.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.aries.fakeinstagramapp.R

class ExploreFragmentContainer : Fragment() {
    lateinit var rootView :View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_container, container, false)
        return rootView
    }
    companion object {
        @JvmStatic
        fun newInstance() = ExploreFragmentContainer()
    }
}
