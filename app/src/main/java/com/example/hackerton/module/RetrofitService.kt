package com.example.hackerton.module

import com.example.hackerton.module.data.request.ExerciseDto
import com.example.hackerton.module.data.request.SaveCalendarDto
import com.example.hackerton.module.data.request.SignInDto
import com.example.hackerton.module.data.response.*
import retrofit2.Call
import retrofit2.http.*
import java.time.LocalDate
import java.util.*

interface RetrofitService {

    @POST("/user/sign-up")
    fun signUp(@Header("Content-Type") json : String,
                @Body req : SignInDto) : Call<SignUpInResponse>

    @POST("/user/sign-in")
    fun signIn(@Header("Content-Type") json: String,
                @Body req : SignInDto) : Call<SignUpInResponse>

    @POST("/manual/")
    fun addManual(@Header("Content-Type") json : String,
                    @Body req : ExerciseDto) : Call<ExerciseResponse>

    @POST("/calendar/")
    fun saveCalendar(@Header("Content-Type") json : String,
                        @Body req : SaveCalendarDto) : Call<SaveCalendarResponse>

    @GET("/manual/")
    fun getAllManual() : Call<GetAllManualResponse>

    @GET("/manual/category/{search-by}")
    fun getManualByCategory(@Path("search-by") category: String) : Call<GetManualByCategoryResponse>

    @GET("/calendar/?")
    fun getCalendar(@Query("date") date : String) : Call<SaveCalendarResponse>

    @PATCH("/manual/modify?")
    fun patchManual (@Query("idx") idx : Int,
                @Body req : ExerciseDto) : Call<ExerciseResponse>

    @PATCH("/calendar/")
    fun patchCalendar(@Body req : SaveCalendarDto) : Call<SaveCalendarResponse>

}