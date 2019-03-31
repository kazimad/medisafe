package com.kazimad.medisafedemo

import android.app.Application
import com.kazimad.medisafedemo.dagger.component.DaggerMainComponent
import com.kazimad.medisafedemo.dagger.component.MainComponent
import com.kazimad.medisafedemo.dagger.module.AppModule
import com.kazimad.medisafedemo.utils.Logger


class App : Application() {


    override fun onCreate() {
        super.onCreate()
        configDagger()
        Logger.init()
        instance = this
    }

    private fun configDagger() {
        mainComponent = DaggerMainComponent.builder()
            .appModule(AppModule())
            .build()

    }

    companion object {
        lateinit var mainComponent: MainComponent
        lateinit var instance: Application
    }
}