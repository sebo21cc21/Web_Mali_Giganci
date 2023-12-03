package com.example.maligiganci

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MemoryChooseMode : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory_choose_mode)
        val button = findViewById<View>(R.id.button_easy_level_memory)
        button.setOnClickListener {
            val intent = Intent(this, MemoryEasyGame::class.java)
            startActivity(intent)
        }

        val button3 = findViewById<View>(R.id.button_hard_level_memory)
        button3.setOnClickListener {
            val intent = Intent(this, MemoryHardGame::class.java)
            startActivity(intent)
        }
    }

}

