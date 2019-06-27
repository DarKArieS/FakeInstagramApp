package com.app.aries.fakeinstagramapp.utilities

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

abstract class FragNaviManager(private val fragmentManager: FragmentManager,
                               private val fragmentContainer:Int,
                               val homeFragmentTag:String)
{
    var currentLateralFragmentTag = homeFragmentTag

    init{
        if (null==fragmentManager.findFragmentById(fragmentContainer)){
            val transaction = fragmentManager.beginTransaction()
            transaction.add(fragmentContainer, createFragment(homeFragmentTag)!!,homeFragmentTag)
            transaction.commit()
        }
    }

    private enum class FragmentStatus{
        SAME,EXIST,ABSENCE;

        var existFragment: Fragment? = null
    }

    fun lateralNavigateTo(
        tag:String,
        addToBackStack: Boolean=false
    ){
        // ToDoDone: study: use show/hide or attach/detach
        // use attach/detach is memory saved but laggy ...
        // attach/detach will remove the fragment from back stack !?
        val status = checkLateralFragmentStatus(tag)
        if(status == FragmentStatus.SAME) return

        val currentFrag = fragmentManager.findFragmentByTag(currentLateralFragmentTag)
        val transaction = fragmentManager.beginTransaction()

        if(currentFrag!=null) transaction.hide(currentFrag)

        when(status){
            FragmentStatus.ABSENCE->{
                status.existFragment = createFragment(tag)
                transaction.add(fragmentContainer,status.existFragment!!,tag)
            }
            FragmentStatus.EXIST->{
                transaction.show(status.existFragment!!)
            }
            else->{}
        }

        if(addToBackStack) transaction.addToBackStack(null)

        transaction.commit()
        currentLateralFragmentTag = tag
    }

    fun lateralOnBackPressed(superCallback:()->Unit){
        val backEntry = fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 1)
        if(backEntry.name == "")
        superCallback.invoke()

        val frag = fragmentManager.findFragmentById(fragmentContainer)
        if(null != frag?.tag && currentLateralFragmentTag != frag.tag){
            currentLateralFragmentTag = frag.tag!!
        }
    }

    fun forwardNavigate(tag:String,addToBackStack: Boolean=false){
        val tr = fragmentManager.beginTransaction()
        tr.replace(fragmentContainer,createFragment(tag)!!,tag)
        if(addToBackStack) tr.addToBackStack(currentLateralFragmentTag)
        tr.commit()
    }

    private fun checkLateralFragmentStatus(tag:String):FragmentStatus{
        val foundFrag = fragmentManager.findFragmentByTag(tag)
        val status = when{
            (foundFrag == null)->FragmentStatus.ABSENCE
            foundFrag.isVisible->FragmentStatus.SAME
            else->FragmentStatus.EXIST
        }
        status.existFragment = foundFrag
        return status
    }

    abstract fun createFragment(tag:String):Fragment?

}