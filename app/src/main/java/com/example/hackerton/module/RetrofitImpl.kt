package com.example.hackerton.module

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitImpl {
    private const val URL = "http://101.101.211.153:8080"

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: RetrofitService = retrofit.create(RetrofitService::class.java)
}