package com.example.sembahyang.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sembahyang.api.ApiService
import com.example.sembahyang.model.AyatResponse
import com.example.sembahyang.model.ModelAyat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AyatViewModel : ViewModel() {
    companion object {
        private val TAG = AyatViewModel::class.java.simpleName
    }

    private val listAyat = MutableLiveData<MutableList<ModelAyat>>()

    fun setDetailSurah(nomor: String?) {
        nomor?.let {
            ApiService.getQuran()
                .getDetailSurah(it)
                .enqueue(object : Callback<AyatResponse> {
                    override fun onResponse(
                        call: Call<AyatResponse>,
                        response: Response<AyatResponse>
                    ) {
                        if (response.isSuccessful) {
                            listAyat.postValue(response.body()?.ayat)
                        }  else {
                            Log.e(TAG, "onFailure: ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<AyatResponse>, t: Throwable) {
                        Log.d(TAG, "failure : ${t.message.toString()}")
                    }

                })
        }
    }

    fun getDetailSurah() : LiveData<MutableList<ModelAyat>> = listAyat


}