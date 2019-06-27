package com.app.aries.fakeinstagramapp.home.profile


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.app.aries.fakeinstagramapp.R
import com.app.aries.fakeinstagramapp.home.GridAdapter
import com.app.aries.fakeinstagramapp.home.PostAdapter
import com.app.aries.fakeinstagramapp.home.StoryAdapter
import com.app.aries.fakeinstagramapp.model.FakeModel
import com.app.aries.fakeinstagramapp.model.PostData
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.story_container.view.*

class ProfileFragment : Fragment(), PostAdapter.Callbacks {
    private lateinit var rootView : View
    private lateinit var gridAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>
    private lateinit var detailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>
    private lateinit var gridLayoutManager : GridLayoutManager
    private lateinit var detailLayoutManager : LinearLayoutManager

    // TODO: Rename and change types of parameters
    private var param1: String? = null

    init{
        Log.d("lifecycle","${this::class.java.name} - ${this.hashCode()} init ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString("GG")
        }
        Log.d("lifecycle","${this::class.java.name} - ${this.hashCode()} onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_profile, container, false)
        Log.d("viewSizeTest","inflate")
        rootView.profileRecyclerView.post {
            Log.d("viewSizeTest","inflated")
            Log.d("viewSizeTest","${rootView.profileRecyclerView.width}")

        }
        Log.d("viewSizeTest","Next")

        setupGridRecyclerView()
        setupDetailRecyclerView()
        showGrid()
        setupBottomNav()
        setupStoryRecyclerView()

        return rootView
    }

    private fun setupBottomNav(){
        // ToDo set selected drawable

        rootView.profileNavigationView.setOnNavigationItemSelectedListener{ item ->
            when (item.itemId) {
                R.id.profNav_grid -> {
                    showGrid()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.profNav_detailed -> {
                    showDetailed()
                    return@setOnNavigationItemSelectedListener true
                }else->{}
            }
            false
        }
    }

    private fun setupGridRecyclerView(){
        val fakeTmp = mutableListOf<PostData>()
        gridAdapter = GridAdapter(
            this.context!!,
            activity!!.window.decorView.measuredWidth / 3,
            FakeModel.getHomePost(2)
        )
        Log.d("viewSizeTest","${activity!!.window.decorView.measuredWidth}")
        gridLayoutManager = GridLayoutManager(this.context!!,3)
    }

    private fun setupDetailRecyclerView(){
        val fakeTmp = mutableListOf<PostData>()

        detailAdapter = PostAdapter(this.context!!, FakeModel.getHomePost(2), this)
        detailLayoutManager = LinearLayoutManager(this.context!!)
    }

    private fun setupStoryRecyclerView(){
        rootView.storyRecyclerView.adapter = StoryAdapter(this.context!!, FakeModel.getRandomTVStory(), 1)
        val layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        rootView.storyRecyclerView.layoutManager = layoutManager
    }

    private fun showGrid(){
        rootView.profileRecyclerView.layoutManager = gridLayoutManager
        rootView.profileRecyclerView.adapter = gridAdapter

    }

    private fun showDetailed(){
        rootView.profileRecyclerView.layoutManager = detailLayoutManager
        rootView.profileRecyclerView.adapter = detailAdapter

    }

    override fun clickUserTitle() {

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString("GG", param1)
                }
            }

        fun newInstance() = ProfileFragment()
    }
}
