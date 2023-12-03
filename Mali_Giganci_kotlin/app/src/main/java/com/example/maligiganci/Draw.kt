package com.example.maligiganci

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Draw : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw)

        val button = findViewById<View>(R.id.button_go_back)
        button.setOnClickListener {
            val intent = Intent(this@Draw, MainActivity::class.java)
            startActivity(intent)
        }
        val button1 = findViewById<View>(R.id.button_play_again)
        button.setOnClickListener {
            val intent = Intent(this@Draw, Memory::class.java)
            startActivity(intent)
        }
    }
}