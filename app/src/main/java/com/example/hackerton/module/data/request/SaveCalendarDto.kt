package com.example.hackerton.module.data.request

import java.time.LocalDate
import java.util.*

data class SaveCalendarDto (var category : String, var date : String, var isExercise : Boolean)