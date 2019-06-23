package com.app.aries.fakeinstagramapp

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.app.aries.fakeinstagramapp.utilities.FragNaviManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var fragNaviManager: FragNaviManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragNaviManager = object: FragNaviManager(
            this.supportFragmentManager,
            R.id.FragmentContainer,
            HomeFragment::class.java.name
        ){
            override fun createFragment(tag: String): Fragment? {
                return when(tag){
                    HomeFragment::class.java.name->HomeFragment.newInstance()
                    else->throw Exception("no such kind of fragment")
                }
            }
        }
    }

    private fun setupBottomNav(){
        // ToDo set selected drawable

        nav_view.setOnNavigationItemSelectedListener{ item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_search -> {
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_add -> {
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_favorite -> {
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_person -> {
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    fun setToolBarFromFragment(toolbar: Toolbar): ActionBar? {
        this.setSupportActionBar(toolbar)

        return this.supportActionBar
    }
}
