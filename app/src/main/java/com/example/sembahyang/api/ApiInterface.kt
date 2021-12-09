package com.example.sembahyang.api


import com.example.sembahyang.model.ModelAyat
import com.example.sembahyang.model.ModelSurah
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/99c279bb173a6e28359c/data")
    fun getListSurah(): Call<ArrayList<ModelSurah>>

    @GET("/99c279bb173a6e28359c/surat/{nomor}")
    fun getDetailSurah(
        @Path("nomor") nomor: String
    ): Call<java.util.ArrayList<ModelAyat>>
}