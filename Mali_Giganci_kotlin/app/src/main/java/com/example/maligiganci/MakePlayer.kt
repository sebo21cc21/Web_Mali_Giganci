package com.example.maligiganci

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.maligiganci.databinding.ActivityMakePlayerBinding
import com.google.firebase.storage.FirebaseStorage
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase

class MakePlayer : AppCompatActivity() {
    private lateinit var binding: ActivityMakePlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakePlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val databaseReference = FirebaseDatabase.getInstance().getReference("blockBaby")
        binding.buttonSubmit.setOnClickListener {
            val name = binding.editTextLogin.text.toString().trim()
            databaseReference.child("name").setValue(name).addOnCompleteListener { }
            val intent = Intent(this@MakePlayer, MainActivity::class.java)
            startActivity(intent)
        }

        binding.changeProfilePicture.setOnClickListener {
            val storageRef = FirebaseStorage.getInstance().reference.child("profile")
            val databaseRef = FirebaseDatabase.getInstance().getReference("blockBaby")

            storageRef.listAll().addOnSuccessListener { listResult ->
                val files = listResult.items
                if (files.isNotEmpty()) {
                    val randomFileRef = files.random()

                    randomFileRef.downloadUrl.addOnSuccessListener { uri ->
                        // Użyj Glide do załadowania zdjęcia
                        Glide.with(this)
                            .load(uri)
                            .into(binding.changeProfilePicture)

                        // Zapisz URL obrazu do Firebase Database
                        databaseRef.child("image").setValue(uri.toString())
                    }
                }
            }
        }


    }
}
