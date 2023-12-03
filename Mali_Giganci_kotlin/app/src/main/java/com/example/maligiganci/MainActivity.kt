package com.example.maligiganci

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private var user: FirebaseUser? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.user_details)
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser
        if (user == null){
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
        else{
            textView.text = user?.email
        }

        val button = findViewById<View>(R.id.imageButton1)
        button.setOnClickListener {
            val intent = Intent(this@MainActivity, Memory::class.java)
            startActivity(intent)
        }
        val button2 = findViewById<View>(R.id.imageButton2)
        button2.setOnClickListener {
            val intent = Intent(this@MainActivity, TicTacToe::class.java)
            startActivity(intent)
        }
        val button3 = findViewById<View>(R.id.imageButton3)
        button3.setOnClickListener {
            val intent = Intent(this@MainActivity, Quiz::class.java)
            startActivity(intent)
        }

        val button4 = findViewById<View>(R.id.logout)
        button4.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }

        }
    }