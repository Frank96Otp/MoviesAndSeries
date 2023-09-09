package com.example.moviesandseries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        screenSplash.setKeepOnScreenCondition{true}
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}