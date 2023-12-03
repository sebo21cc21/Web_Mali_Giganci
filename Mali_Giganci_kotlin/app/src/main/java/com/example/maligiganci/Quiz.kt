package com.example.maligiganci

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Quiz : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_main)
    val button = findViewById<View>(R.id.button_play)
    button.setOnClickListener {
        val intent = Intent(this@Quiz, QuizActivity::class.java)
        startActivity(intent) }
    val button2 = findViewById<View>(R.id.button_play_math)
    button2.setOnClickListener {
        val intent = Intent(this@Quiz, QuizActivityMath::class.java)
        startActivity(intent) }
    val button3 = findViewById<View>(R.id.button_play_english)
    button3.setOnClickListener {
        val intent = Intent(this@Quiz, QuizActivityEnglish::class.java)
        startActivity(intent) }
}
}