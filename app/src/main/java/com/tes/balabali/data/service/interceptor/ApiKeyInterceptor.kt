package com.tes.balabali.data.service.interceptor

import com.tes.balabali.data.common.Constants.QUERY_PARAM_API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader(
            QUERY_PARAM_API_KEY, "application/vnd.github.v3+json"
        ).build()
        return chain.proceed(builder.build())
    }
}