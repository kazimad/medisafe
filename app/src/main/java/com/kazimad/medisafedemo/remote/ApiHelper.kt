package com.kazimad.medisafedemo.remote

import com.kazimad.medisafedemo.models.error.ResponseException
import io.reactivex.functions.Predicate
import retrofit2.Response
import java.net.HttpURLConnection

class ApiHelper {

    companion object {
        private const val RESPONSE_CODE_BAD_REQUESTS = HttpURLConnection.HTTP_BAD_REQUEST
        fun <T : Any> baseApiFilterPredicate(): Predicate<Response<T>> {
            return Predicate {
                if (it.code() >= RESPONSE_CODE_BAD_REQUESTS) {
                    throw ResponseException("BAD_REQUESTS", it.code())
                }
                if (it.body() == null) {
                    throw ResponseException(it.message(), it.code())
                }
                true
            }
        }
    }
}