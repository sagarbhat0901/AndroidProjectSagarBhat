package com.example.projectsagarbhat



import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread
import android.animation.ValueAnimator

/**
 * SplashActivity to display a splash screen while loading the main activity.
 */
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enable edge-to-edge display for immersive experience
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        // Find TextView for displaying countdown
        val splashScreenText = findViewById<TextView>(R.id.textViewTimer)

        // Coroutine to update countdown text every second
        GlobalScope.launch {
            for (i in 3 downTo 1) {
                delay(1000)
                launch(Dispatchers.Main) {
                    splashScreenText.text = i.toString()
                }
            }
        }

        // Thread to delay splash screen for 5 seconds before starting main activity
        thread {
            Thread.sleep(5000)
            val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }

        // Find ProgressBar for showing progress animation
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        // Function to start progress bar animation
        fun startProgressBarAnimation() {
            // Create a ValueAnimator for animating the progress
            val animator = ValueAnimator.ofInt(0, 100).apply {
                duration = 5000 // 5000 milliseconds = 5 seconds
                addUpdateListener { valueAnimator ->
                    progressBar.progress = valueAnimator.animatedValue as Int
                }
            }
            // Start the animator
            animator.start()
        }
        startProgressBarAnimation() // Start progress bar animation
    }
}

