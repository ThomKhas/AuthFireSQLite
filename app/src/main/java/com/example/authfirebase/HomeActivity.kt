package com.example.authfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.authfirebase.SignInActivity.Companion.EXTRA_NAME
import com.example.authfirebase.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        supportActionBar?.hide()
        binding.textView5.text = intent.getStringExtra(EXTRA_NAME)
        binding.btnSignOut.setOnClickListener {

            auth.signOut()

            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Has cerrado tu sesion", Toast.LENGTH_SHORT).show()

            finish()
        }

    }
}