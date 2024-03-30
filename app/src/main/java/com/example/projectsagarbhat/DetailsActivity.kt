package com.example.projectsagarbhat


import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * DetailsActivity responsible for displaying details of a selected image.
 */
class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge display for immersive experience
        enableEdgeToEdge()
        setContentView(R.layout.details_layout)

        // Apply window insets to adjust padding to accommodate system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views
        val backButton = findViewById<Button>(R.id.buttonBack)
        val idTextView = findViewById<TextView>(R.id.textViewImageID)
        val titleTextView = findViewById<TextView>(R.id.textViewImageTitle)
        val urlTextView = findViewById<TextView>(R.id.textViewImageUrl)

        // Retrieve data passed from previous activity
        val myBundle = intent.extras
        val idIntent = myBundle?.getString("keyId")
        val titleIntent = myBundle?.getString("keyTitle")
        val urlIntent = myBundle?.getString("keyUrl")

        // Display details in TextViews
        idTextView.text = idIntent
        titleTextView.text = titleIntent
        urlTextView.text = urlIntent

        // Handle back button click
        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}
