package com.movie.android.data

import com.movie.android.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()
        val requestBuilder = original.newBuilder()
            .url(originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY).build())
        return chain.proceed(requestBuilder.build())
    }
}