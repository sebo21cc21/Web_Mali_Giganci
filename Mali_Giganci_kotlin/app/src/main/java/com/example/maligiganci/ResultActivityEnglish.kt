package com.example.maligiganci

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ResultActivityEnglish : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_result)
        val tv_name: TextView = findViewById(R.id.tv_name)
        val tv_score: TextView = findViewById(R.id.tv_score)
        val btn_finish: Button = findViewById(R.id.btn_finish)
        // Hide the status bar and get the details from intent and set it to the UI. And also add a click event to the finish button.
        // START
        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val userName = intent.getStringExtra(ConstantsEnglish.USER_NAME)
        tv_name.text = userName

        val totalQuestions = intent.getIntExtra(ConstantsEnglish.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(ConstantsEnglish.CORRECT_ANSWERS, 0)

        tv_score.text = "Twój wynik to $correctAnswers z $totalQuestions."

        btn_finish.setOnClickListener {
            startActivity(Intent(this@ResultActivityEnglish, MainActivity::class.java))
        }
        // END
    }
}