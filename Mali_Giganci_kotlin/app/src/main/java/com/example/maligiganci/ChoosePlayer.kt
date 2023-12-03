package com.example.maligiganci

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ChoosePlayer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_player)

        val button = findViewById<View>(R.id.button_easy_level)
        button.setOnClickListener {
            val intent = Intent(this, GameTicTacToeEasy::class.java)
            startActivity(intent)
        }
        val button2 = findViewById<View>(R.id.button_medium_level)
        button2.setOnClickListener {
            val intent = Intent(this, GameTicTacToeMedium::class.java)
            startActivity(intent)
        }
        val button3 = findViewById<View>(R.id.button_hard_level)
        button3.setOnClickListener {
            val intent = Intent(this, GameTicTacToeHard::class.java)
            startActivity(intent)
        }
    }
}