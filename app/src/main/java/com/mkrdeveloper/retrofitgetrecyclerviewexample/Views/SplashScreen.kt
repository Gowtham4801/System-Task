package com.mkrdeveloper.retrofitgetrecyclerviewexample.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.mkrdeveloper.retrofitgetrecyclerviewexample.R

class SplashScreen : AppCompatActivity() {

    lateinit var textViewAnimation : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        textViewAnimation = findViewById(R.id.tv_splash_screen)

        textViewAnim()
        navigateToMainActivity()
    }


    private fun navigateToMainActivity() {
        Handler(Looper.getMainLooper()).postDelayed(object : Runnable {
            override fun run() {
                val intent = Intent(this@SplashScreen, MainActivity::class.java)
                startActivity(intent)
                finish()
                print("hi")
            }
        }, 3000)
    }

    fun textViewAnim(){
        val textViewAnim: Animation =
            AnimationUtils.loadAnimation(this, R.anim.zoom_in_anime)
        textViewAnimation.startAnimation(textViewAnim)

    }
}