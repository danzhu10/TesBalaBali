package com.tes.balabali.data.di

import com.tes.balabali.data.ApiRepositoryImpl
import com.tes.balabali.data.service.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {

    single { get<Retrofit>().create(ApiService::class.java) }

    single {
        ApiRepositoryImpl(get())
    }

}