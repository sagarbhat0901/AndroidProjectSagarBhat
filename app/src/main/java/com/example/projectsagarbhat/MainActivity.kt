package com.example.projectsagarbhat

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        /*  ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        var userName = findViewById<EditText>(R.id.editTextUsername)
        var password = findViewById<EditText>(R.id.editTextPassword)
        var signUpButton = findViewById<Button>(R.id.buttonSignUp)
        var loginButton = findViewById<Button>(R.id.buttonLogin)
        var myIntent = Intent(this, SignUpActivity::class.java)
        var db = Room.databaseBuilder(this, MyDB::class.java, "mydatabase")
            .fallbackToDestructiveMigration().build()
        var h = Handler()

        signUpButton.setOnClickListener {
            startActivity(myIntent)
        }

        loginButton.setOnClickListener {

            var username = userName.text.toString()
            var pass = password.text.toString()

//            val checkUsers: List<MyEntity> = db.myDao().checkUser(username, pass)
            thread {
                val y = db.myDao().checkUser(username, pass)
                if (y.isEmpty()){
                        h.post {
                            Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else {
                        h.post {
                            Toast.makeText(this, "Logged In Successfully", Toast.LENGTH_SHORT).show()

                            startActivity(Intent(this@MainActivity, Dashboard::class.java))
                        }
                    }
                }
            }
        }
    }
