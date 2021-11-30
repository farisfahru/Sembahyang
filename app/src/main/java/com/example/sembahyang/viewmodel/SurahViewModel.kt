package com.example.sembahyang.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sembahyang.api.ApiService
import com.example.sembahyang.model.ModelSurah
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SurahViewModel : ViewModel() {
    companion object {
        private val TAG = SurahViewModel::class.java.simpleName
    }

    private val listSurah = MutableLiveData<ArrayList<ModelSurah>>()

    fun setSurah(query: String) {
        query.let {
            ApiService.getQuran()
                .getListSurah()
                .enqueue(object : Callback<ArrayList<ModelSurah>> {
                    override fun onResponse(
                        call: Call<ArrayList<ModelSurah>>,
                        response: Response<ArrayList<ModelSurah>>
                    ) {
                        if (response.isSuccessful) {
                            val items: ArrayList<ModelSurah> = ArrayList(response.body())
                            listSurah.postValue(items)
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<ModelSurah>>, t: Throwable) {
                        Log.d(TAG, "failure : ${t.message.toString()}")
                    }

                })
        }
    }


    fun getSurah(): LiveData<ArrayList<ModelSurah>> = listSurah

}