package com.example.sembahyang.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    companion object {
        fun getQuran(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.npoint.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}