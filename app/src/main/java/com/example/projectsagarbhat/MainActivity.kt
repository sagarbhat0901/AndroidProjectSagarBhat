package com.example.projectsagarbhat

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.projectsagarbhat.database.MyDB
import kotlin.concurrent.thread

/**
 * Main activity responsible for user login.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enable edge-to-edge display for immersive experience
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize UI elements
        val userName = findViewById<EditText>(R.id.editTextUsername)
        val password = findViewById<EditText>(R.id.editTextPassword)
        val signUpButton = findViewById<Button>(R.id.buttonSignUp)
        val loginButton = findViewById<Button>(R.id.buttonLogin)
        val signUpIntent = Intent(this, SignUpActivity::class.java)

        // Create database instance
        val db = Room.databaseBuilder(this, MyDB::class.java, "mydatabase")
            .fallbackToDestructiveMigration().build()

        // Handler to update UI from background thread
        val handler = Handler()

        // Set click listener for sign up button
        signUpButton.setOnClickListener {
            startActivity(signUpIntent)
        }

        // Set click listener for login button
        loginButton.setOnClickListener {
            // Retrieve username and password input
            val username = userName.text.toString()
            val pass = password.text.toString()

            // Background thread for database operation
            thread {
                // Check if user exists in the database
                val userExists = db.myDao().checkUser(username, pass)

                // Update UI based on database query result
                handler.post {
                    if (userExists.isEmpty()) {
                        // If user does not exist, display toast message
                        Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
                    } else {
                        // If user exists, display login success message and navigate to Dashboard
                        Toast.makeText(this, "Logged In Successfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@MainActivity, Dashboard::class.java))
                    }
                }
            }
        }
    }
}

