package com.ewelaw.newrelease.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.ewelaw.newrelease.R
import com.ewelaw.newrelease.customview.CustomProgressBar
import com.ewelaw.newrelease.databinding.ActivitySplashScreenBinding
import org.koin.android.ext.android.bind

class SplashScreenActivity : AppCompatActivity() {
    private val splashTime = 5000L
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        findViewById<CustomProgressBar>(R.id.progress_bar).animateProgress()
        val topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        binding.ivIcon.animation = topAnim
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }, splashTime)
    }
}