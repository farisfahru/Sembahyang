package com.example.sembahyang.api


import com.example.sembahyang.model.AyatResponse
import com.example.sembahyang.model.ModelDoa
import com.example.sembahyang.model.ModelSurah
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("api/surat")
    fun getListSurah(): Call<ArrayList<ModelSurah>>

    @GET("api/surat/{nomor}")
    fun getDetailSurah(
        @Path("nomor") nomor: String
    ): Call<AyatResponse>

    @GET("api")
    fun getListDoa(): Call<ArrayList<ModelDoa>>
}