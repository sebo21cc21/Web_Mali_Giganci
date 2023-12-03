package com.example.maligiganci

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.maligiganci.databinding.ActivityMakePlayerBinding
import com.google.firebase.storage.FirebaseStorage
import com.bumptech.glide.Glide

class MakePlayer : AppCompatActivity() {
    private lateinit var binding: ActivityMakePlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakePlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.changeProfilePicture.setOnClickListener{
            val storageRef = FirebaseStorage.getInstance().reference.child("profile")
            storageRef.listAll().addOnSuccessListener { listResult ->
                val files = listResult.items
                if (files.isNotEmpty()) {
                    val randomFileRef = files.random()

                    randomFileRef.downloadUrl.addOnSuccessListener { uri ->
                        // Użyj Glide do załadowania zdjęcia
                        Glide.with(this)
                            .load(uri)
                            .into(binding.changeProfilePicture)
                    }.addOnFailureListener {
                        // Obsługa błędów, np. logowanie błędu
                    }
                } else {
                    // Brak plików w folderze
                }
            }.addOnFailureListener {
                // Obsługa błędów, np. logowanie błędu
            }
        }
    }
}
