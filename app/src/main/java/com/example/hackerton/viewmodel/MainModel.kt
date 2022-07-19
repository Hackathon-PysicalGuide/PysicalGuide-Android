package com.example.hackerton.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hackerton.module.RetrofitImpl
import com.example.hackerton.module.data.ManualData
import com.example.hackerton.module.data.response.GetAllManualResponse
import com.example.hackerton.module.data.response.GetManualByCategoryResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainModel : ViewModel() {

    private val service = RetrofitImpl.service
    var allData = MutableLiveData<List<ManualData>>()

    fun refresh() {
        getData()
    }


    private fun getData() {
        val job = CoroutineScope(Dispatchers.IO).launch{
            service.getAllManual().enqueue(object : Callback<GetAllManualResponse> {
                override fun onResponse(call: Call<GetAllManualResponse>, response: Response<GetAllManualResponse>) {
                    allData.value = response.body()?.list
                    Log.e("전체 조회 성공", response.body().toString())
                }
                override fun onFailure(call: Call<GetAllManualResponse>, t: Throwable) { Log.e("전체 조회 실패", t.message.toString()) }
            })
        }
    }

    fun getDataByCategory(category: String) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            service.getManualByCategory(category).enqueue( object : Callback<GetManualByCategoryResponse>{
                override fun onResponse(call: Call<GetManualByCategoryResponse>, response: Response<GetManualByCategoryResponse>) { allData.value = response.body()!!.list }
                override fun onFailure(call: Call<GetManualByCategoryResponse>, t: Throwable) { Log.e("카테고리 조회 실패", t.message.toString()) }
            })
        }
    }




}