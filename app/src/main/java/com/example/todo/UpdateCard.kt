package com.example.todo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.todo.databinding.ActivityUpdateCardBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateCard :AppCompatActivity() {
    private lateinit var database: MyDatabase

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityUpdateCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(
            applicationContext,MyDatabase::class.java,"To_Do"
        ).build()


        val pos = intent.getIntExtra("id",-1)
        if(pos!=-1){

            val title = DataObject.getData(pos).title
            val priority = DataObject.getData(pos).priority

            binding.createTitle.setText(title)
            binding.createPriority.setText(priority)

            binding.deleteButton.setOnClickListener {
                val updatedTitle = binding.createTitle.text.toString()
                val updatedPriority = binding.createPriority.text.toString()

                DataObject.deleteData(pos)

                GlobalScope.launch{
                    database.dao().deleteTask(
                        Entity(pos+1,updatedTitle,
                            updatedPriority)
                    )

                }
                myIntent()
            }

            binding.updateButton.setOnClickListener {
                val updatedTitle = binding.createTitle.text.toString()
                val updatedPriority = binding.createPriority.text.toString()

                DataObject.updateData(
                    pos,
                    updatedTitle,
                    updatedPriority
                )

                GlobalScope.launch{
                    database.dao().updateTask(
                        Entity(
                            pos+1,updatedTitle,
                            updatedPriority
                        )
                    )

                }

                myIntent()
            }

        }
    }

    fun myIntent(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}