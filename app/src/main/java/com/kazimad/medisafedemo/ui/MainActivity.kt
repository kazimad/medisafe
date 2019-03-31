package com.kazimad.medisafedemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kazimad.medisafedemo.R
import com.kazimad.medisafedemo.utils.ActivityUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActivityUtils.addFragmentToActivity(this, MainFragment(), true)
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 1) {
            finish()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}
