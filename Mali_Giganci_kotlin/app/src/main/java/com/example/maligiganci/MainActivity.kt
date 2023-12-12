package com.example.maligiganci

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private var user: FirebaseUser? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val textView = findViewById<TextView>(R.id.user_details)
//        auth = FirebaseAuth.getInstance()
//        user = auth.currentUser
//        if (user == null){
//            val intent = Intent(this, Login::class.java)
//            startActivity(intent)
//            finish()
//        }
//        else{
//            textView.text = user?.email
//            saveEmailToFirebase(user?.email)
//        }

        val flagRef = FirebaseDatabase.getInstance().getReference("blockBaby/flag")

        flagRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val flag = dataSnapshot.getValue(Boolean::class.java)

                if (flag == false) {
                    // If flag is false, block the activities and show the lock message
                    findViewById<View>(R.id.imageButton1).isEnabled = false
                    findViewById<View>(R.id.imageButton2).isEnabled = false
                    findViewById<View>(R.id.imageButton3).isEnabled = false
                    findViewById<TextView>(R.id.block).text = "BLOKADA RODZICIELSKA"
                } else {
                    // If flag is true, enable the activities
                    findViewById<View>(R.id.imageButton1).isEnabled = true
                    findViewById<View>(R.id.imageButton2).isEnabled = true
                    findViewById<View>(R.id.imageButton3).isEnabled = true
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })


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
        val button5 = findViewById<View>(R.id.button_contact)
        button5.setOnClickListener {
            val intent = Intent(this@MainActivity, Contact::class.java)
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
    private fun saveEmailToFirebase(email: String?) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("blockBaby")
        email?.let {
            databaseReference.child("email").setValue(it)
        }
    }
    }