package com.example.hackerton.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackerton.R
import com.example.hackerton.databinding.ActivityMainBinding
import com.example.hackerton.view.adapter.Adapter
import com.example.hackerton.view.adapter.Deco
import com.example.hackerton.viewmodel.MainModel
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bind : ActivityMainBinding
    private val model : MainModel by viewModels()
    private lateinit var navigate : NavigationView
    private lateinit var drawer : DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        drawer = findViewById(R.id.main_drawer)
        navigate = findViewById(R.id.drawer_menu)

        val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        bind.mainHamberger.setOnClickListener {
            drawer.openDrawer(navigate)
        }
        navigate.setNavigationItemSelectedListener {
            if( it.itemId == R.id.calendar ){
                startActivity(Intent(this, CalendarActivity::class.java))
                drawer.closeDrawers()
                return@setNavigationItemSelectedListener true
            } else if(it.itemId == R.id.write){
                startActivity(Intent(this, WriteActivity::class.java))
                drawer.closeDrawers()
                return@setNavigationItemSelectedListener true
            }
            false
        }

        bind.lifecycleOwner = this

        bind.mainList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        bind.mainList.addItemDecoration(Deco())

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

        model.refresh()
        observeAllData()
    }

    private fun observeAllData() {
        model.allData.observe(this, Observer {
            bind.mainList.adapter = Adapter(it, this)
        })
    }

}