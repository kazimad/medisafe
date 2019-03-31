package com.kazimad.medisafedemo.utils

import android.util.Log
import com.kazimad.medisafedemo.BuildConfig

class Logger {
    companion object {
        private var LOGS_ENABLED = true

        fun init() {
            LOGS_ENABLED =
                BuildConfig.LOGS_ENABLED
        }

        fun log(_message: String) {
            Log.d(Constants.MY_LOG, _message)
        }

        fun log(_tag: String, _message: String) {
            Log.d(_tag, _message)
        }
    }
}
