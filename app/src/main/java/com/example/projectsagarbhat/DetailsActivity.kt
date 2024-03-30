package com.example.projectsagarbhat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.details_layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        var backButton = findViewById<Button>(R.id.buttonBack)
        var idTextView = findViewById<TextView>(R.id.textViewImageID)
        var titleTextView = findViewById<TextView>(R.id.textViewImageTitle)
        var urlTextView = findViewById<TextView>(R.id.textViewImageUrl)

        var mybundle = intent.extras
        var idIntent = mybundle?.getString("keyId")
        var titleIntent = mybundle?.getString("keyTitle")
        var urlIntent = mybundle?.getString("keyUrl")

        idTextView.setText(idIntent)
        titleTextView.setText(titleIntent)
        urlTextView.setText(urlIntent)

        backButton.setOnClickListener{
            this.onBackPressed()
        }
    }
}