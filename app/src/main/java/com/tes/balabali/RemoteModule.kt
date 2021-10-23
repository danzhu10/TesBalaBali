package com.tes.balabali

import com.tes.balabali.data.common.Constants
import com.tes.balabali.data.service.RetrofitFactory
import com.tes.balabali.data.service.interceptor.ApiKeyInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import java.util.concurrent.TimeUnit


val remoteModule = module {

    single {
        RetrofitFactory.create(Constants.BASE_URL, get())
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(
                ApiKeyInterceptor()
            )
            .connectTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)
            .build()
    }
}