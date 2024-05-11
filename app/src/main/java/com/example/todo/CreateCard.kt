package com.example.todo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.databinding.ActivityCreateCardBinding

class CreateCard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityCreateCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            if(binding.createTitle.text.toString().trim{it<=' '}.isNotEmpty()
                && binding.createPriority.text.toString().trim{it<=' '}.isNotEmpty())
            {
                var title = binding.createTitle.getText().toString()
                var priority = binding.createPriority.getText().toString()
                DataObject.setData(title, priority)
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}