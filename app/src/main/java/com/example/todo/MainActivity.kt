package com.example.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.add.setOnClickListener{
            val intent = Intent(this,CreateCard::class.java)
            startActivity(intent)
        }


        binding.deleteAll.setOnClickListener {
        DataObject.deleteAll()

        }

        binding.recyclerView.adapter = Adapter(DataObject.getAllData())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}