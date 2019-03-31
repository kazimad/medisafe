package com.kazimad.medisafedemo.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kazimad.medisafedemo.App
import com.kazimad.medisafedemo.models.response.CountryDetailed
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DetailCountryFragmentViewModel @Inject constructor() : ViewModel() {

    var resultLiveData = MutableLiveData<ArrayList<CountryDetailed>>()
    var errorLiveData = MutableLiveData<Throwable>()
    var compositeDisposable = CompositeDisposable()

    fun getCountryDetails(countryName:String) {
        App.mainComponent.getCountyRepository().getDetailedData(countryName)
            .subscribe(
                { result -> run { resultLiveData.value = result } },
                { error -> errorLiveData.value = error })?.let { compositeDisposable.add(it) }
    }

    fun disposeAll() {
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }

}