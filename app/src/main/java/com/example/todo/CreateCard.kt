package com.example.todo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.todo.databinding.ActivityCreateCardBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateCard : AppCompatActivity() {

    private lateinit var database: myDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityCreateCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(
            applicationContext,myDatabase::class.java,"To_Do"
        ).build()

        binding.saveButton.setOnClickListener {
            if(binding.createTitle.text.toString().trim{it<=' '}.isNotEmpty()
                && binding.createPriority.text.toString().trim{it<=' '}.isNotEmpty())
            {
                var title = binding.createTitle.getText().toString()
                var priority = binding.createPriority.getText().toString()
                DataObject.setData(title, priority)

                GlobalScope.launch {
                    database.dao().insertTask(Entity(0,title, priority))
                    Log.i("harsh",database.dao().getTasks().toString())
                }

                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}