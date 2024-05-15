package com.example.quizapp

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat

class QuizActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val quizPositionString: String? = intent?.getStringExtra("selectedPosition") // value passed from SecondActivity
        Log.d("intent", intent.toString())
        Log.d("quizPositionString", quizPositionString.toString())
        var quizPosition: Int? = null
        try {
            quizPosition = quizPositionString?.toInt()
        } catch(e: NumberFormatException) {
            Log.e("ERROR CRASH", "quizPosition passed to QuizActivivity cannot be converted to Int.")
        }
        val quizData: QuizData? = if(quizPosition != null) QuizPicker(quizPosition).selected else null


        // get the view elements of the quiz screen
        val tvQuestion = findViewById<TextView>(R.id.tvQuestion)
        val tvOption1 = findViewById<TextView>(R.id.tvOption1)
        val tvOption2 = findViewById<TextView>(R.id.tvOption2)
        val tvOption3 = findViewById<TextView>(R.id.tvOption3)
        val tvOption4 = findViewById<TextView>(R.id.tvOption4)
        val btnSelect = findViewById<Button>(R.id.btnSelect)

        // if questionData is null, then no such data exists, throw exception as
        // this is an error in the program and is not expected
        if(quizData == null || quizData.questionData.isEmpty()){
            // go to previous activity
            Toast.makeText(this, "The quiz is empty.", Toast.LENGTH_SHORT).show()
            finish()
        }
        val questions: ArrayList<QuestionData> = quizData?.questionData as ArrayList<QuestionData>

        fun showQuestions(questionDataArray: ArrayList<QuestionData>) {

            Log.d("Question first", questionDataArray[0].questionText.toString())

            val questionData = questionDataArray[0]
            tvQuestion.text = questionData.questionText
            val tvOptions = arrayOf(tvOption1, tvOption2, tvOption3, tvOption4)
            tvOption1.text = questionData.option1
            tvOption2.text = questionData.option2
            tvOption3.text = questionData.option3
            tvOption4.text = questionData.option4
            btnSelect.text = "SELECT" // todo use R class

            // remove any option text view background colors from potential previous questions
            // and reset all textSizes to default and the text styles (bold)
            tvOptions.forEach { it.background = resources.getDrawable(R.drawable.option_background); it.textSize = 30f; it.setTypeface(it.typeface, Typeface.NORMAL) }
            // todo fix bold text reset
            var finishedSelection = false
            var indexSelected: Int? = null
            tvOptions.forEachIndexed { tvIndex, tvOption ->
                // set action listeners
                tvOption.setOnClickListener {
                    if(!finishedSelection) {
                        // set the text style to bold
                        tvOption.setTypeface(tvOption.typeface, Typeface.BOLD)
                        // save the selected index
                        indexSelected = tvIndex // todo test if that makes sense
                        // display the selected option as picked, by increasing text size and make it bold
                        // readjust the other options to have their original size
                        tvOptions.forEachIndexed { index, option ->  if(index == indexSelected)  option.textSize = 35f
                        else option.textSize = 30f}
                    }
                }
            }
            btnSelect.setOnClickListener {
                // if no option is selected, just notify the user to select an option
                if(indexSelected == null) {
                    Toast.makeText(this, "Please select one of the options.", Toast.LENGTH_SHORT).show()
                }
                else {
                    finishedSelection = true
                    // regardless of what is pressed, display the correct answer with a green background

                    tvOptions[questionData.answer.value].setBackgroundColor(resources.getColor(R.color.greenCorrect))

                    //  todo set background to a green or red drawable or think how to change the solid attribute of the drawable
                   // tvOptions[questionData.answer.value].background = resources.getDrawable(R.drawable.newBackground_here)

                    // if the selected option is incorrect, add a red background to it
                    if(questionData.answer.value != indexSelected) {
                        tvOptions[indexSelected!!].setBackgroundColor(resources.getColor(R.color.redWrong))
                    }
                    btnSelect.text = if(questionDataArray.size == 1) "FINISH" else "CONTINUE" // todo use R class
                    btnSelect.setOnClickListener {
                        Log.d("Clicked continue", true.toString())
                        questionDataArray.removeAt(0)
                        // quiz ends, go to next activity
                        if(questionDataArray.isEmpty()) {
                            // todo create the result activity to display after the quiz
                            // todo think of how to remember the quiz results
                            startActivity(Intent(this, SecondActivity::class.java))
                        } else {
                            showQuestions(questionDataArray)
                        }
                    }
                }
            }
        }
        showQuestions(questions)
    }
}