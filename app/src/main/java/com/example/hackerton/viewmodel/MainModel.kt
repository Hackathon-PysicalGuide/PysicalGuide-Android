package com.example.hackerton.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hackerton.module.RetrofitImpl
import com.example.hackerton.module.data.ManualData
import com.example.hackerton.module.data.request.ExerciseDto
import com.example.hackerton.module.data.response.ExerciseResponse
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
    var allData = MutableLiveData<List<ExerciseDto>>()
    var categoryData = MutableLiveData<List<ManualData>>()

    fun refresh() {
        getData()
    }


    private fun getData() {
        val job = CoroutineScope(Dispatchers.IO).launch{
            service.getAllManual().enqueue(object : Callback<GetAllManualResponse> {
                override fun onResponse(call: Call<GetAllManualResponse>, response: Response<GetAllManualResponse>) { allData.value = response.body()!!.list }
                override fun onFailure(call: Call<GetAllManualResponse>, t: Throwable) { Log.e("실패", t.message.toString()) }
            })
        }
    }

    fun getDataByCategory(category: String) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            service.getManualByCategory(category).enqueue( object : Callback<GetManualByCategoryResponse>{
                override fun onResponse(call: Call<GetManualByCategoryResponse>, response: Response<GetManualByCategoryResponse>) { categoryData.value = response.body()!!.manualData }
                override fun onFailure(call: Call<GetManualByCategoryResponse>, t: Throwable) { Log.e("실패", t.message.toString()) }
            })
        }
    }




}