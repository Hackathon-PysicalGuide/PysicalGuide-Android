package com.example.hackerton.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.MutableLiveData
import com.example.hackerton.R
import com.example.hackerton.module.ContentType
import com.example.hackerton.module.RetrofitImpl
import com.example.hackerton.module.data.request.SaveCalendarDto
import com.example.hackerton.module.data.response.SaveCalendarResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CalendarActivity : AppCompatActivity() {

    private val service = RetrofitImpl.service
    private var string : String = ""
    private lateinit var category : String

    private var text = MutableLiveData<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        val calendar = findViewById<CalendarView>(R.id.calendar)
        val save = findViewById<Button>(R.id.save_calendar)
        val patch = findViewById<Button>(R.id.patch_calendar)
        val spinner = findViewById<Spinner>(R.id.calendar_spinner)
        var calendartext = findViewById<TextView>(R.id.calendar_text)

        spinner.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.array,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item
        )
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when (p2) {
                    0 -> { category = "upperBody" }
                    1 -> { category = "lowerBody" }
                    2 -> { category = "abs" }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) { category = "upperBody" }

        }


        calendar.setOnDateChangeListener { calendarView, year, month, day ->

            if(month < 10 && day < 10) string = "${year}-0${month}-0${day}"
            else if(month < 10) { string = "${year}-0${month}-${day}" }
            else if(day < 10) { string = "${year}-${month}-0${day}" }
            Log.e("날짜", string)

            refresh()

            spinner.visibility = View.VISIBLE
            save.visibility = View.VISIBLE
            patch.visibility = View.VISIBLE
            calendartext.visibility = View.VISIBLE
        }

        save.setOnClickListener {
            val req = SaveCalendarDto(category, string, false)
            service.saveCalendar(ContentType.CONTENT_TYPE, req)
                .enqueue(object : Callback<SaveCalendarResponse> {
                    override fun onResponse(call: Call<SaveCalendarResponse>, response: Response<SaveCalendarResponse>) {
                        Log.e("성공", "데이터 저장 성공")
                        refresh()
                        spinner.visibility = View.GONE
                    }

                    override fun onFailure(call: Call<SaveCalendarResponse>, t: Throwable) {
                        Log.e("실패", t.message.toString())
                    }

                })
        }

        patch.setOnClickListener {
            val req = SaveCalendarDto(category, string, false)
            service.patchCalendar(req).enqueue(object : Callback<SaveCalendarResponse>{
                override fun onResponse(call: Call<SaveCalendarResponse>, response: Response<SaveCalendarResponse>) {
                    Log.e("성공", "데이터 수정 성공")
                    refresh()
                    spinner.visibility = View.GONE
                }

                override fun onFailure(call: Call<SaveCalendarResponse>, t: Throwable) {

                }

            })
        }

        text.observe(this, androidx.lifecycle.Observer {
            calendartext.text = text.value.toString()
        })
    }

    fun refresh() {
        CoroutineScope(Dispatchers.IO).launch {
            service.getCalendar(string).enqueue(object : Callback<SaveCalendarResponse> {
                override fun onResponse(
                    call: Call<SaveCalendarResponse>,
                    response: Response<SaveCalendarResponse>
                ) {
                    Log.e("가져오기 성공", "리얼")
                    when (response.body()?.calendarData?.category) {
                        null -> text.value = "일정이 없습니다."
                        "lowerBody" ->{ text.value = "오늘 해야할 운동 : 하체"

                        }

                        "upperBody" -> text.value = "오늘 해야할 운동 : 상체"
                        "abs" -> text.value = "오늘 해야할 운동 : 복근"
                    }
                }

                override fun onFailure(call: Call<SaveCalendarResponse>, t: Throwable) {}
            })
        }
    }
}
