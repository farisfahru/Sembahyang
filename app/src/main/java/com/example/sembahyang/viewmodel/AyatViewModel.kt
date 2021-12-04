package com.example.sembahyang.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sembahyang.api.ApiService
import com.example.sembahyang.model.ModelAyat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AyatViewModel : ViewModel() {
    companion object {
        private val TAG = AyatViewModel::class.java.simpleName
    }

    private val listAyat = MutableLiveData<ArrayList<ModelAyat>>()

    fun setDetailSurah(nomor: String?) {
        nomor?.let {
            ApiService.getQuran()
                .getDetailSurah(it)
                .enqueue(object : Callback<ArrayList<ModelAyat>> {
                    override fun onResponse(
                        call: Call<ArrayList<ModelAyat>>,
                        response: Response<ArrayList<ModelAyat>>
                    ) {
                        val items: ArrayList<ModelAyat> = ArrayList(response.body())
                        listAyat.postValue(items)
                    }

                    override fun onFailure(call: Call<ArrayList<ModelAyat>>, t: Throwable) {
                        Log.d(TAG, "failure : ${t.message.toString()}")
                    }

                })
        }
    }

    fun getDetailSurah() : LiveData<ArrayList<ModelAyat>> = listAyat


}