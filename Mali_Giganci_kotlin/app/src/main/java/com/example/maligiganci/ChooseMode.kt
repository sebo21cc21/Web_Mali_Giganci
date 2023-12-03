package com.example.maligiganci

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ChooseMode : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_mode)

        val button1 = findViewById<View>(R.id.imageButtonOnePlayer)
        button1.setOnClickListener {
            val intent = Intent(this, ChoosePlayer::class.java)
            startActivity(intent)
            }
        val button2 = findViewById<View>(R.id.imageButtonTwoPlayers)
        button2.setOnClickListener {
            val intent = Intent(this, GameTicTacToeTwoPlayers::class.java)
            startActivity(intent)
        }
        val button3 = findViewById<View>(R.id.ButtonOnePlayer)
        button3.setOnClickListener {
            val intent = Intent(this, ChoosePlayer::class.java)
            startActivity(intent)
        }
        val button4 = findViewById<View>(R.id.ButtonTwoPlayers)
        button4.setOnClickListener {
            val intent = Intent(this, GameTicTacToeTwoPlayers::class.java)
            startActivity(intent)
        }
    }
}