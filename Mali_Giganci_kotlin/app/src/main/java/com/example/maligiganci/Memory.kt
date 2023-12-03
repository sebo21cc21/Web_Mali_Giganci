package com.example.maligiganci

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Memory : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory)

    val button = findViewById<View>(R.id.button_play)
    button.setOnClickListener {
        val intent = Intent(this@Memory, MemoryChooseMode::class.java)
        startActivity(intent) }
    }
}