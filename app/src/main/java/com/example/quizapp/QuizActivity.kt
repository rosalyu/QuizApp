package com.example.quizapp

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class QuizActivity(val quizPicker: QuizPicker): AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val quizData: QuizData? = quizPicker.selected
        val tvQuestion = findViewById<TextView>(R.id.tvQuestion)

        // if questionData is null, then no such data exists, throw exception as
        // this is an error in the program and is not expected
        val questionData: Array<QuestionData> = quizData?.questionData
            ?: throw NoSuchElementException("Error: Selected quiz does not exist.")
        if(questionData.isEmpty()){
            // go to previous activity
            Toast.makeText(this, "The quiz is empty.", Toast.LENGTH_SHORT).show()
            finish()
        }
        for(question in questionData) {

        }
    }
}