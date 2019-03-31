package com.kazimad.medisafedemo.remote

import com.kazimad.medisafedemo.models.response.Country
import com.kazimad.medisafedemo.models.response.CountryDetailed
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiSource {

    companion object {
        const val baseUrl = "https://restcountries.eu/"
    }

    @GET("/rest/v2/all?fields=name;nativeName;flag;population")
    fun getList(): Observable<Response<ArrayList<Country>>>

    @GET("/rest/v2/name/{name}")
    fun getDetails(@Path("name") name: String): Observable<Response<ArrayList<CountryDetailed>>>
}