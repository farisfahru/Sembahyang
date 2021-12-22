package com.example.sembahyang.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    companion object {
        fun getQuran(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://equran.id/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiInterface::class.java)
        }

        fun getDoa(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://doa-doa-api-ahmadramadhan.fly.dev/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}