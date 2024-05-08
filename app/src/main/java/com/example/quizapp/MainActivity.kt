package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textInputField: EditText = findViewById(R.id.textInputField)
        val startButton: Button = findViewById(R.id.startButton)
        val secondActivity = Intent(this, SecondActivity::class.java)

        startButton.setOnClickListener {
            // if the user is empty, ask user to enter his name with a Toast
            if(textInputField.text.isEmpty()) {
                Toast.makeText(this, "Please enter your name.", Toast.LENGTH_SHORT).show()
            }
            // name too long
            else if(textInputField.text.length > 30) {
                Toast.makeText(this, "Name can't contain more than 30 characters.", Toast.LENGTH_SHORT).show()
            }
            else {
                // pass the value to SecondActivity
                secondActivity.putExtra("userName", textInputField.text.toString())
                // go to next activity
                startActivity(secondActivity)

            }
        }
    }
}
