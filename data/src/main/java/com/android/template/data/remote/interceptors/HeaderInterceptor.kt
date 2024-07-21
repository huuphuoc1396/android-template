package com.android.template.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal class HeaderInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json")
            .build()
        return chain.proceed(request)
    }
}