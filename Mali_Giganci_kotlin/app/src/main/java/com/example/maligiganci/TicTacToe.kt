package com.example.maligiganci

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class TicTacToe : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac_toe)
        Log.d("TAG", "GameTicTacToe onCreate called")

        val button = findViewById<View>(R.id.buttonPlay)
        button.setOnClickListener {
            val intent = Intent(this, ChooseMode::class.java)
            startActivity(intent)
        }
    }
}
