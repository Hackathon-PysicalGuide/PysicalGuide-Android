package com.example.hackerton.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.hackerton.R
import com.example.hackerton.module.data.ManualData
import com.example.hackerton.module.data.request.ExerciseDto
import com.example.hackerton.view.ViewActivity

class Adapter(private val list : List<ManualData>, val  context : Context) : RecyclerView.Adapter<com.example.hackerton.view.adapter.Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_item,parent,false)
        return ViewHolder(view).apply {

            itemView.setOnClickListener {
                // 뷰 눌렀을시 작동
                val intent = Intent(context, ViewActivity::class.java)
                intent.putExtra("title", title.text.toString())
                intent.putExtra("content", content)
                intent.putExtra("category", category.text.toString())
                intent.putExtra("idx", Integer.parseInt(idx.text.toString()))
                ContextCompat.startActivity(context, intent, null)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = list[position].title
        when (list[position].category) {
            "abs" -> holder.category.text = "복근"
            "upperBody" -> holder.category.text = "상체"
            "lowerBody" -> holder.category.text = "하체"
        }
        holder.idx.text = list[position].idx.toString()
        holder.content = list[position].content
    }

    override fun getItemCount(): Int {return list.size}


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var title: TextView = view.findViewById(R.id.item_title)
        var category: TextView = view.findViewById(R.id.item_category)
        var idx: TextView = view.findViewById(R.id.idx)
        var content : String = "default"
    }
}