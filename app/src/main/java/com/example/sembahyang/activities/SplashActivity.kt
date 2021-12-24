package com.example.sembahyang.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.sembahyang.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var splashBinding: ActivitySplashBinding
    private lateinit var handler: Handler
    private val mDelay = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        supportActionBar?.hide()

        handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, mDelay)
    }
}