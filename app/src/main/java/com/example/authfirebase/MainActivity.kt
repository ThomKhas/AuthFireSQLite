package com.example.authfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //this use to hide action bar
        supportActionBar?.hide()
            //use to put delay
        Handler(Looper.getMainLooper()).postDelayed({
            //use to start an activity
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            //use to destroy current activity
            finish()
        },3000) // 3000 millis = 3 seconds

    }
}