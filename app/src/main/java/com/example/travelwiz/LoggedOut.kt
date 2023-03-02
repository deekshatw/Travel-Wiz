package com.example.travelwiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoggedOut : AppCompatActivity() {

    private lateinit var signInButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_out)

        signInButton = findViewById(R.id.signInButton)

        signInButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}