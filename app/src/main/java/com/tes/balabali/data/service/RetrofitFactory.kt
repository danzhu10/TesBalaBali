package com.tes.balabali.data.service

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    fun create(baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
//        Timber.d("tokenNih %s", userManager.getToken())
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(
                okHttpClient
            )
            .build()
    }
}
