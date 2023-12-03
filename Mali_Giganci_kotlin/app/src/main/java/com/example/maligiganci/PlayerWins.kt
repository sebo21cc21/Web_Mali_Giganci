package com.example.maligiganci

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class PlayerWins : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_wins)

        val button = findViewById<View>(R.id.button_go_back)
        button.setOnClickListener {
            val intent = Intent(this@PlayerWins, MainActivity::class.java)
            startActivity(intent)
        }
        val button1 = findViewById<View>(R.id.button_play_again)
        button.setOnClickListener {
            val intent = Intent(this@PlayerWins, Memory::class.java)
            startActivity(intent)
        }
    }

}