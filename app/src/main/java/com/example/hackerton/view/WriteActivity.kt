package com.example.hackerton.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.hackerton.R
import com.example.hackerton.module.data.request.ExerciseDto
import com.example.hackerton.viewmodel.WriteModel

class WriteActivity : AppCompatActivity() {

    private lateinit var category : String
    private val model = WriteModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        val spinner = findViewById<Spinner>(R.id.spiner)
        val title = findViewById<TextView>(R.id.write_title).text.toString()
        val content = findViewById<TextView>(R.id.write_content).text.toString()
        val save = findViewById<Button>(R.id.write_btn)

        spinner.adapter = ArrayAdapter.createFromResource(this,R.array.array, com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when (p2){
                    0 -> { category = "upperBody" }
                    1 -> { category = "lowerBody" }
                    2 -> { category = "abs" }
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        save.setOnClickListener {
            if(category.isEmpty() || title.isEmpty() || content.isEmpty()) {
                Toast.makeText(this,"카테고리, 제목, 내용을 모두 작성해 주세요", Toast.LENGTH_SHORT).show()
            }else {
                val data = ExerciseDto(title, content, category)
                model.saveManual(data)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}