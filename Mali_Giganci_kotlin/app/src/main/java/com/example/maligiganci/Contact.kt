package com.example.maligiganci

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Contact : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        auth = FirebaseAuth.getInstance()

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view_kontakty)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchChildData()
    }

    private fun fetchChildData() {
        val databaseReference = FirebaseDatabase.getInstance().getReference("blockBaby")
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val childName = snapshot.child("name").getValue(String::class.java) ?: "Unknown"
                val childImageUrl = snapshot.child("image").getValue(String::class.java) ?: ""

                val contact = ContactClass(
                    "Email: ${auth.currentUser?.email ?: "brak@email.com"}",
                    "Imię dziecka: ${childName}",
                    childImageUrl
                )

                recyclerView.adapter = ContactAdapter(listOf(contact))
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle possible errors
            }
        })
    }
}
