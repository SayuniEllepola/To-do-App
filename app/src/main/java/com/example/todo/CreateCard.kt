package com.example.todo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.todo.databinding.ActivityCreateCardBinding
import kotlinx.coroutines.*

class CreateCard : AppCompatActivity() {

    private lateinit var database: MyDatabase

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityCreateCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(
            applicationContext,MyDatabase::class.java,"To_Do"
        ).build()

        binding.saveButton.setOnClickListener {
            if(binding.createTitle.text.toString().trim{it<=' '}.isNotEmpty()
                && binding.createPriority.text.toString().trim{it<=' '}.isNotEmpty())
            {
                var title = binding.createTitle.getText().toString()
                var priority = binding.createPriority.getText().toString()
                DataObject.setData(title, priority)

               GlobalScope.launch {
                    database.dao().insertTask(Entity(0, title, priority))
                }


                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}