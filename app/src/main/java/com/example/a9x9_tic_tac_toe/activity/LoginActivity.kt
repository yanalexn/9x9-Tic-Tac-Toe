package com.example.a9x9_tic_tac_toe.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.a9x9_tic_tac_toe.DB
import com.example.a9x9_tic_tac_toe.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseDatabase
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance(DB)
        dbRef = db.reference
    }

    fun btnLoginEvent(view: View) {
        loginToFireBase("${binding.etEmail.text}", "${binding.etPassword.text}")
    }

    fun loginToFireBase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                val toastAfterLogin = if (task.isSuccessful) "Successful login" else "Failed login"
                Toast.makeText(applicationContext, toastAfterLogin, Toast.LENGTH_LONG)
                    .show()
                if (task.isSuccessful) {
                    val currentUser = auth.currentUser
                    if (currentUser != null) {
                        dbRef.child("user")
                            .child(currentUser.email ?: "")
                            .child("request")
                            .setValue(currentUser.email)
                    }
                    loadMain()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        loadMain()
    }

    private fun loadMain() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", currentUser.email)
            intent.putExtra("uid", currentUser.uid)
            startActivity(intent)
        }
    }
}