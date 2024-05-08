package com.example.quizapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.StringBuilder

class SecondActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val userName = intent.getStringExtra("userName") // value passed from MainActivity
        // set the TextView with the username that the user typed in the field from the MainActivity
        findViewById<TextView>(R.id.tvHello).text = StringBuilder(getString(R.string.hello))
            .append(userName).append(getString(R.string.exclamation))



    }
}