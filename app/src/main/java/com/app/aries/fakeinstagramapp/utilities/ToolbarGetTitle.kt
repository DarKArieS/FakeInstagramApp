package com.app.aries.fakeinstagramapp.utilities

import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class ToolbarGetTitle(private val toolbar: Toolbar) {

    fun findTitle(): TextView? {
        for (i in 0 until toolbar.childCount) {
            val child = toolbar.getChildAt(i)
            if ( child is TextView && child.text == toolbar.title)
                return child
        }
        return null
    }

    fun findSubTitle(): TextView? {
        for (i in 0 until toolbar.childCount) {
            val child = toolbar.getChildAt(i)
            if ( child is TextView && child.text == toolbar.subtitle)
                return child
        }
        return null
    }




}