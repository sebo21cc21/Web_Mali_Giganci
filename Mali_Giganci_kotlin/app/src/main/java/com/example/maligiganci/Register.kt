package com.example.maligiganci

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {
    private lateinit var editTextEmial: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var auth: FirebaseAuth
    private lateinit var progressBAr: ProgressBar
    private lateinit var textView: TextView

    //Sprawdzenie czy użytkownbik jest już zalogowany
    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        editTextEmial = findViewById(R.id.email)
        editTextPassword = findViewById(R.id.password)
        progressBAr = findViewById(R.id.progressBar)
        textView = findViewById(R.id.loginNow)
        auth = FirebaseAuth.getInstance()

        textView.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }



        val button = findViewById<View>(R.id.btn_register)
        button.setOnClickListener {
            progressBAr.setVisibility(View.VISIBLE)
            val email = editTextEmial.text.toString()
            val password = editTextPassword.text.toString()

            if (TextUtils.isEmpty(email)){
                Toast.makeText(this@Register, "Podaj email", Toast.LENGTH_SHORT).show();
            }

            if (TextUtils.isEmpty(password)){
                Toast.makeText(this@Register, "Podaj hasło", Toast.LENGTH_SHORT).show();
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        progressBAr.setVisibility(View.GONE)
                        val user = auth.currentUser
                        Toast.makeText(
                            this@Register,
                            "Konto zostało stworzone.",
                            Toast.LENGTH_SHORT,
                        ).show()

                        // Po zarejestrowaniu użytkownika, przenieś go do aktywności MakePlayer
                        val intent = Intent(this, MakePlayer::class.java)
                        startActivity(intent)
                    } else {
                        progressBAr.setVisibility(View.GONE)
                        Toast.makeText(
                            this@Register,
                            "Rejestracja nieudana.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }
    }
}