package com.example.hackerton.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.hackerton.module.ContentType
import com.example.hackerton.module.RetrofitImpl
import com.example.hackerton.module.data.request.ExerciseDto
import com.example.hackerton.module.data.response.ExerciseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WriteModel : ViewModel() {

    private val service = RetrofitImpl.service

    fun saveManual(req : ExerciseDto) {
        service.addManual(ContentType.CONTENT_TYPE, req).enqueue( object : Callback<ExerciseResponse> {
            override fun onResponse(call: Call<ExerciseResponse>, response: Response<ExerciseResponse>) {
                Log.e("저장 성공", "성공")
            }

            override fun onFailure(call: Call<ExerciseResponse>, t: Throwable){
                Log.e("저장 실패", t.message.toString())
            }

        })
    }

    fun patchManual(idx : Int, req : ExerciseDto) {
        service.patchManual(idx, req).enqueue(object : Callback<ExerciseResponse>{
            override fun onResponse(
                call: Call<ExerciseResponse>,
                response: Response<ExerciseResponse>
            ) {
                Log.e("수정 성공", "성공")
            }

            override fun onFailure(call: Call<ExerciseResponse>, t: Throwable) {
                Log.e("수정 실패", t.message.toString())
            }

        })
    }
}