package com.example.maligiganci

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class YouLose : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_you_lose)

        val button = findViewById<View>(R.id.button_go_back)
        button.setOnClickListener {
            val intent = Intent(this@YouLose, MainActivity::class.java)
            startActivity(intent)
        }
        val button1 = findViewById<View>(R.id.button_play_again)
        button.setOnClickListener {
            val intent = Intent(this@YouLose, Memory::class.java)
            startActivity(intent)
        }
    }
}