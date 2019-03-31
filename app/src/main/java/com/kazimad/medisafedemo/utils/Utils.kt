package com.kazimad.medisafedemo.utils

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.kazimad.medisafedemo.R
import com.pixplicity.sharp.Sharp
import okhttp3.*
import java.io.IOException


object Utils {
    private var httpClient: OkHttpClient? = null

    fun fetchSvg(url: String, target: ImageView) {
        if (httpClient == null) {
            httpClient = OkHttpClient.Builder()
                .cache(Cache(target.context.cacheDir, 5 * 1024 * 1014))
                .build()
        }

        val request = Request.Builder().url(url).build()
        httpClient?.newCall(request)?.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                target.setImageDrawable(ContextCompat.getDrawable(target.context, R.drawable.ic_no_image))
            }

            override fun onResponse(call: Call, response: Response) {
                val stream = response.body()?.byteStream()
                try {
                    Sharp.loadInputStream(stream).into(target)
                } catch (ex: Exception) {
                    Logger.log("onResponse ${ex.message}")
                } finally {
                    stream?.close()
                }

            }
        })
    }

}