package com.example.projectsagarbhat

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        var usernameEditText = findViewById<EditText>(R.id.editTextUsername1)
        var passwordEditText = findViewById<EditText>(R.id.editTextPassword1)
        var AgeEditText = findViewById<EditText>(R.id.editTextAge)
        var FullNameEditText = findViewById<EditText>(R.id.editTextFullName)
        var db = Room.databaseBuilder(this,MyDB::class.java,"mydatabase")
            .fallbackToDestructiveMigration().build()
        var myIntent2 = Intent(this,MainActivity::class.java)
        var h = Handler()


        var submitButton = findViewById<Button>(R.id.buttonSubmit)
        submitButton.setOnClickListener{
            var myUsername = usernameEditText.text.toString()
            var myPassword = passwordEditText.text.toString()
            var myAge = AgeEditText.text.toString()
            var myFullName = FullNameEditText.text.toString()


            GlobalScope.launch {
                var data: List<MyEntity>? = db.myDao().readData()
                var users = MyEntity()
                users.myUsername = myUsername
                users.myPassword = myPassword
                users.myAge = myAge
                users.myFullName = myFullName
                var check: Int = 1
                if (data == null) {
                    h.post {
                        Toast.makeText(
                            this@SignUpActivity,
                            "Please Enter valid data",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    data.forEach {
                        if (myUsername.equals(it.myUsername)) {
                            check = 0
                        }
                    }
                }
                if (check == 1) {
                    db.myDao().saveData(users)
                    h.post{Toast.makeText(this@SignUpActivity,"Details Submitted",Toast.LENGTH_LONG).show()}
                    startActivity(myIntent2)
                } else {
                    h.post{Toast.makeText(this@SignUpActivity,"Username is already taken",Toast.LENGTH_LONG).show()}

                }
            }
            usernameEditText.setText("")
            passwordEditText.setText("")
            AgeEditText.setText("")
            FullNameEditText.setText("")

        }

        }
    }
