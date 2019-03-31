package com.kazimad.medisafedemo.dagger.component

import com.kazimad.medisafedemo.dagger.module.AppModule
import com.kazimad.medisafedemo.remote.ApiSource
import com.kazimad.medisafedemo.remote.CountriesRepository

import dagger.Component

@Component(modules = [AppModule::class])
interface MainComponent {

    fun getApi(): ApiSource
    fun getCountyRepository(): CountriesRepository
}