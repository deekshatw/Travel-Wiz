package com.example.travelwiz

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            val iHome = Intent(this, MainActivity::class.java)
            startActivity(iHome)
            finish()
        }, 4000)
    }

    override fun onStart() {
        super.onStart()
        if (!checkLogin){
            Handler(Looper.getMainLooper()).postDelayed({
                val iHome = Intent(this, HomeActivity::class.java)
                startActivity(iHome)
                finish()
            }, 4000)
        }
    }
}