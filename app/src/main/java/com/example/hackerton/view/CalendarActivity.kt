package com.example.hackerton.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.hackerton.R
import java.text.SimpleDateFormat
import java.util.*


class CalendarActivity : AppCompatActivity() {

    private lateinit var date : Date
    private lateinit var category : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        val calendar = findViewById<CalendarView>(R.id.calendar)
        val save = findViewById<Button>(R.id.save_calendar)
        val patch = findViewById<Button>(R.id.patch_calendar)
        val spinner = findViewById<Spinner>(R.id.calendar_spinner)

        spinner.adapter = ArrayAdapter.createFromResource(this, R.array.array, com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when (p2) {
                    0 -> {category = "upperBody"}
                    1 -> {category = "lowerBody"}
                    2 -> {category = "abs"}
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }


        calendar.setOnDateChangeListener { calendarView, year, month, day ->
            val string = "${year}-${month}-${day}"
            val formatter = SimpleDateFormat("yyyy-mm-dd")
            date = formatter.parse(string)!!

        }

    }
}