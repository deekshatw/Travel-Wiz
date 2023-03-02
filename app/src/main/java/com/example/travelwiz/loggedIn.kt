package com.example.travelwiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class loggedIn : AppCompatActivity() {

    private lateinit var exploreBtn : Button
    private lateinit var logOutBtn : Button

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)

        val email = intent.getStringExtra("email")
        val displayName = intent.getStringExtra("name")

        exploreBtn = findViewById(R.id.exploreBtn)
        logOutBtn = findViewById(R.id.logOutBtn)
        exploreBtn.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        logOutBtn.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoggedOut::class.java ))
        }

        auth = FirebaseAuth.getInstance()
    }
}