package com.app.aries.fakeinstagramapp


import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {
    lateinit var rootView:View
    private var mainActivity : MainActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        if (context is MainActivity) {
            mainActivity = (this.activity as MainActivity)
        }else mainActivity = null
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_home, container, false)

        val actionBar = mainActivity?.setToolBarFromFragment(rootView.homeToolbar)
        //actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_photo_camera_black_24dp)
        rootView.homeToolbar.menu
        this.setHasOptionsMenu(true)

        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_appbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()

    }
}
