package com.example.todo

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.ViewBinding


class Adapter(var data:List<CardInfo>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(private val binding: ViewBinding):RecyclerView.ViewHolder(binding.root){
        var title = binding.title
        var priority = binding.priority
        var layout = binding.mylayout

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        when(data[position].priority.toLowerCase())
        {
            "high" ->holder.layout.setBackgroundColor(Color.parseColor("#F05454"))
            "medium" ->holder.layout.setBackgroundColor(Color.parseColor("#EDC988"))
            else-> holder.layout.setBackgroundColor(Color.parseColor("#F05454"))
        }


        holder.title.text = data[position].title
        holder.priority.text = data[position].priority
        holder.itemView.setOnClickListener {

            val intent = Intent(holder.itemView.context,UpdateCard::class.java)
            intent.putExtra("id",position)
            holder.itemView.context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
       return data.size
    }
}