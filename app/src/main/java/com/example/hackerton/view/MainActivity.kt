package com.example.hackerton.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackerton.R
import com.example.hackerton.databinding.ActivityMainBinding
import com.example.hackerton.view.adapter.Adapter
import com.example.hackerton.view.adapter.CategoryAdapter
import com.example.hackerton.viewmodel.MainModel

class MainActivity : AppCompatActivity() {

    private lateinit var bind : ActivityMainBinding
    private val model : MainModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.lifecycleOwner = this

        model.refresh()


        bind.mainList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        bind.writeBtn.setOnClickListener { startActivity(Intent(this, WriteActivity::class.java)) }

        bind.mainSpinner.adapter = ArrayAdapter.createFromResource(this, R.array.main_array, com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
        bind.mainSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when (p2) {
                    0 -> { model.refresh() }
                    1 ->{ model.getDataByCategory("upperBody") }
                    2 ->{ model.getDataByCategory("lowerBody") }
                    3 ->{ model.getDataByCategory("abs") }
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        observeAllData()
    }

    private fun observeAllData() {
        model.allData.observe(this, Observer {
            bind.mainList.adapter = Adapter(it)
        })
    }

    private fun observeCategoryData() {
        model.categoryData.observe(this, Observer {
            bind.mainList.adapter = CategoryAdapter(it)
        })
    }
}