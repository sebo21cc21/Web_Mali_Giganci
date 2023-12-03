package com.example.maligiganci

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult


class Login : AppCompatActivity() {
    private lateinit var editTextEmail: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var progressBAr: ProgressBar
    private lateinit var textView: TextView


    //Sprawdzenie czy użytkownbik jest już zalogowany
    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun signInGoogle(){
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if (result.resultCode == Activity.RESULT_OK){

            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful){
            val account : GoogleSignInAccount? = task.result
            if (account != null){
                updateUI(account)
            }
        }else{
            Toast.makeText(this, task.exception.toString() , Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken , null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful){
                val intent : Intent = Intent(this , MainActivity::class.java)
                intent.putExtra("email" , account.email)
                intent.putExtra("name" , account.displayName)
                startActivity(intent)
            }else{
                Toast.makeText(this, it.exception.toString() , Toast.LENGTH_SHORT).show()

            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextEmail = findViewById(R.id.email)
        editTextPassword = findViewById(R.id.password)
        progressBAr = findViewById(R.id.progressBar)
        textView = findViewById(R.id.registerNow)
        auth = FirebaseAuth.getInstance()

        textView.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
            finish()
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("361827856884-vpseeij0q5t5lhbkbqpvm37l8e8209sv.apps.googleusercontent.com")
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this,gso)

        val button_google = findViewById<View>(R.id.btn_login_google)
        button_google.setOnClickListener{
            signInGoogle()
        }


        val button_fb = findViewById<View>(R.id.btn_login_facebook)
        button_fb.setOnClickListener {
            // Rozpocznij proces logowania przez Facebook
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email", "public_profile"))

            // Dodaj CallbackManager do obsługi wyników logowania
            val callbackManager = CallbackManager.Factory.create()
            LoginManager.getInstance().registerCallback(callbackManager, object :
                FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    // Logowanie przez Facebook zakończone sukcesem
                    val accessToken = loginResult.accessToken
                    val intent = Intent(this@Login, MainActivity::class.java)
                    intent.putExtra("email", "email_value") // Przykładowe dane do przekazania
                    intent.putExtra("name", "name_value") // Przykładowe dane do przekazania
                    startActivity(intent)                }

                override fun onCancel() {
                    // Obsługa anulowania logowania przez Facebook
                    Toast.makeText(this@Login, "Logowanie przez Facebook anulowane", Toast.LENGTH_SHORT).show()
                }

                override fun onError(error: FacebookException) {
                    // Obsługa błędu logowania przez Facebook
                    Toast.makeText(this@Login, "Błąd logowania przez Facebook: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }


        val button = findViewById<View>(R.id.btn_login)
        button.setOnClickListener {
            progressBAr.setVisibility(View.VISIBLE)
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this@Login, "Podaj email", Toast.LENGTH_SHORT).show();
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this@Login, "Podaj hasło", Toast.LENGTH_SHORT).show();
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        progressBAr.setVisibility(View.GONE)
                        Toast.makeText(
                            this@Login,
                            "Zalogowano pomyślnie",
                            Toast.LENGTH_SHORT,
                        ).show()

                        val intent = Intent(this, Login::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        progressBAr.setVisibility(View.GONE)
                        Toast.makeText(
                            this@Login,
                            "Logowanie nieudane",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }

        }
    }
}