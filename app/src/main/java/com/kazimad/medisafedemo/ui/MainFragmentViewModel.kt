package com.kazimad.medisafedemo.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kazimad.medisafedemo.App
import com.kazimad.medisafedemo.models.response.Country
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor() : ViewModel() {

    var resultLiveData = MutableLiveData<ArrayList<Country>>()
    var errorLiveData = MutableLiveData<Throwable>()
    var compositeDisposable = CompositeDisposable()


    fun getAllCountries() {
        App.mainComponent.getCountyRepository().getListWithData()
            .subscribe(
                { result -> run { resultLiveData.value = result } },
                { error -> errorLiveData.value = error })?.let { compositeDisposable.add(it) }
    }

    fun disposeAll() {
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }
}