package com.kazimad.medisafedemo.models.error


class ResponseException(val errorMessage: String?, val code: Int?) : Exception()

