package com.example.hackerton.module

import com.example.hackerton.module.data.request.ExerciseDto
import com.example.hackerton.module.data.request.SignInDto
import com.example.hackerton.module.data.response.ExerciseResponse
import com.example.hackerton.module.data.response.GetAllManualResponse
import com.example.hackerton.module.data.response.GetManualByCategoryResponse
import com.example.hackerton.module.data.response.SignUpInResponse
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @POST("/user/sign-up")
    fun signUp(@Header("Content-Type") json : String,
                @Body req : SignInDto) : Call<SignUpInResponse>

    @POST("/user/sign-in")
    fun signIn(@Header("Content-Type") json: String,
                @Body req : SignInDto) : Call<SignUpInResponse>

    @POST("/manual")
    fun addManual(@Header("Content-Type") json : String,
                    @Body req : ExerciseDto) : Call<ExerciseResponse>

    @POST("/manual/schedule/{userId}")
    fun addSchedule(@Header("Content-Type") json : String)

    @GET("/exercise/schedule")
    fun getSchedule()

    @GET("/manual")
    fun getAllManual() : Call<GetAllManualResponse>

    @GET("/manual/category?searchBy=")
    fun getManualByCategory(@Query("searchBy") category: String) : Call<GetManualByCategoryResponse>

    @PATCH("/manual/modify?idx=")
    fun patchManual (@Query("idx") idx : Int,
                @Body req : ExerciseDto) : Call<ExerciseResponse>

}