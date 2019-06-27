package com.app.aries.fakeinstagramapp.home.home


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.aries.fakeinstagramapp.MainActivity
import com.app.aries.fakeinstagramapp.R
import com.app.aries.fakeinstagramapp.home.PostAdapter
import com.app.aries.fakeinstagramapp.model.FakeModel
import com.app.aries.fakeinstagramapp.model.PostData
import com.app.aries.fakeinstagramapp.home.profile.ProfileFragment
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment(), PostAdapter.Callbacks {
    lateinit var rootView:View
    lateinit var postData: List<PostData>
    private var mainActivity : MainActivity? = null

    init{
        Log.d("lifecycle","${this::class.java.name} - ${this.hashCode()} init ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postData = FakeModel.getHomePost()
        Log.d("lifecycle","${this::class.java.name} - ${this.hashCode()} onCreate")
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
        Log.d("lifecycle","${this::class.java.name} - ${this.hashCode()} onCreateView")
        rootView = inflater.inflate(R.layout.fragment_home, container, false)

        setupToolbar()
        setupRecyclerView()

        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_appbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setupToolbar(){
        val actionBar = mainActivity?.setToolBarFromFragment(rootView.homeToolbar)
        //actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_photo_camera_black_24dp)
        rootView.homeToolbar.menu
        this.setHasOptionsMenu(true)
    }

    private fun setupRecyclerView(){
        rootView.mainRecyclerView.adapter =
            PostAdapter(this.context!!, postData, this, true)
        val layoutManager = LinearLayoutManager(this.context!!)
        rootView.mainRecyclerView.layoutManager = layoutManager
    }

    override fun clickUserTitle() {
        //mainActivity?.fragNaviManager?.navigateTo(ProfileFragment::class.java.name, true)
        mainActivity?.fragNaviManager?.forwardNavigate(ProfileFragment::class.java.name,true)
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}
