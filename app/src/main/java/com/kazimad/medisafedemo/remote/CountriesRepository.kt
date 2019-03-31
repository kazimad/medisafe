package com.kazimad.medisafedemo.remote


import com.kazimad.medisafedemo.models.response.Country
import com.kazimad.medisafedemo.models.response.CountryDetailed
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class CountriesRepository @Inject constructor(apiSource: ApiSource) {

    private var apiSource: ApiSource = apiSource
    fun getListWithData(): Observable<ArrayList<Country>?> {
        return apiSource.getList()
            .filter(ApiHelper.baseApiFilterPredicate())
            .subscribeOn(Schedulers.io())
            .flatMap { Observable.fromArray(it.body()) }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getDetailedData(countryName:String): Observable<ArrayList<CountryDetailed>?> {
        return apiSource.getDetails(countryName)
            .filter(ApiHelper.baseApiFilterPredicate())
            .subscribeOn(Schedulers.io())
            .flatMap { Observable.fromArray(it.body()) }
            .observeOn(AndroidSchedulers.mainThread())
    }
}