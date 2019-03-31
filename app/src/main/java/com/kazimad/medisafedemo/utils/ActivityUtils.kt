package com.kazimad.medisafedemo.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.kazimad.medisafedemo.R


object ActivityUtils {

    fun addFragmentToActivity(activity: FragmentActivity, fragment: Fragment, addToBackStack: Boolean) {
        addFragmentToActivity(activity, fragment, addToBackStack, R.id.fragmentContainer)
    }

    private fun addFragmentToActivity(activity: FragmentActivity, fragment: Fragment, addToBackStack: Boolean, containerId: Int) {
        val fragmentManager = activity.supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(containerId, fragment)
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commitAllowingStateLoss()
    }

}