package com.example.hackerton.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hackerton.R
import com.example.hackerton.module.data.ManualData

class CategoryAdapter(val list : List<ManualData>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){
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

        when (list[position].category) {
            "abs" -> holder.category.text = "복근"
            "upperBody" -> holder.title.text = "상체"
            "lowerBody" -> holder.title.text = "하체"
        }
    }

    override fun getItemCount(): Int {return list.size}


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var title = view.findViewById<TextView>(R.id.item_title)
        var category = view.findViewById<TextView>(R.id.item_category)

    }
}