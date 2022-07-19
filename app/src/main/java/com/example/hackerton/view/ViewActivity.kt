package com.example.hackerton.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hackerton.R
import com.example.hackerton.databinding.ActivityMainBinding
import com.example.hackerton.databinding.ActivityViewBinding

class ViewActivity : AppCompatActivity() {

    private lateinit var bind : ActivityViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        bind = ActivityViewBinding.inflate(layoutInflater)
        setContentView(bind.root)



        val result = intent
        val idx = intent.getIntExtra("idx",0)
        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        val category = intent.getStringExtra("category")

        bind.viewTitle.text = title
        bind.viewContent.text = content

        bind.editPost.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra("idx", idx)
            intent.putExtra("title", title)
            intent.putExtra("content", content)
            intent.putExtra("category", category)
            startActivity(intent)
        }

    }
}