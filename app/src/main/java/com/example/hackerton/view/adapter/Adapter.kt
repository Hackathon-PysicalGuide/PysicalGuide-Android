package com.example.hackerton.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hackerton.R
import com.example.hackerton.module.data.ManualData
import com.example.hackerton.module.data.request.ExerciseDto

class Adapter(val list : List<ExerciseDto>) : RecyclerView.Adapter<com.example.hackerton.view.adapter.Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_item,parent,false)
        return ViewHolder(view).apply {
            itemView.setOnClickListener {
                // 뷰 눌렀을시 작동
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = list[position].title
        holder.category.text = list[position].category
    }

    override fun getItemCount(): Int {return list.size}


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var title = view.findViewById<TextView>(R.id.item_title)
        var category = view.findViewById<TextView>(R.id.item_category)

    }
}