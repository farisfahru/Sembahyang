package com.example.sembahyang.api


import com.example.sembahyang.model.ModelSurah
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("/99c279bb173a6e28359c/data")
    fun getListSurah(): Call<ArrayList<ModelSurah>>
}