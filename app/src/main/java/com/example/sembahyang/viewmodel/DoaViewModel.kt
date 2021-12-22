package com.example.sembahyang.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sembahyang.api.ApiService
import com.example.sembahyang.model.ModelDoa
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DoaViewModel : ViewModel() {

    companion object {
        private val TAG = DoaViewModel::class.java.simpleName
    }

    private val listDoa = MutableLiveData<ArrayList<ModelDoa>>()

    fun setDoa(query: String) {
        query.let {
            ApiService.getDoa()
                .getListDoa()
                .enqueue(object : Callback<ArrayList<ModelDoa>> {
                    override fun onResponse(
                        call: Call<ArrayList<ModelDoa>>,
                        response: Response<ArrayList<ModelDoa>>
                    ) {
                        if (response.isSuccessful) {
                            val items: ArrayList<ModelDoa> = ArrayList(response.body())
                            listDoa.postValue(items)
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<ModelDoa>>, t: Throwable) {
                        Log.d(TAG, "failure : ${t.message.toString()}")
                    }

                })
        }
    }


    fun getDoa(): LiveData<ArrayList<ModelDoa>> = listDoa

}