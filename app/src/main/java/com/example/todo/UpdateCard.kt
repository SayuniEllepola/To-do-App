package com.example.todo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.databinding.ActivityUpdateCardBinding

class UpdateCard :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityUpdateCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pos = intent.getIntExtra("id",-1)
        if(pos!=-1){

            val title = DataObject.getData(pos).title
            val priority = DataObject.getData(pos).priority

            binding.createTitle.setText(title)
            binding.createPriority.setText(priority)

            binding.deleteButton.setOnClickListener {
                DataObject.deleteData(pos)
                myIntent()
            }

            binding.updateButton.setOnClickListener {
                DataObject.updateData(pos,title, priority)
                myIntent()
            }

        }
    }

    fun myIntent(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}