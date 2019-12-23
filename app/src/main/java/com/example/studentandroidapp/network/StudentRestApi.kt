package com.example.studentandroidapp.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object StudentRestApi {

    fun createRetrofitService() : StudentRestApiService = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:3000")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(StudentRestApiService::class.java)
}

