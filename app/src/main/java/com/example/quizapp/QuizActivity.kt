package com.example.quizapp

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

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
            Log.e("ERROR CRASH", "quizPosition passed to QuizActivity cannot be converted to Int.")
        }
        val quizData: QuizData? = if(quizPosition != null) QuizPicker(quizPosition).selected else null


        // get the view elements of the quiz screen
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val tvProgress = findViewById<TextView>(R.id.tvProgress)
        val btnAudio = findViewById<ImageButton>(R.id.btnAudio)
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
        // set the progress bar maximum value
        progressBar.max = questions.size
        // show initial progress
        tvProgress.text = StringBuilder("0/").append(progressBar.max)

        fun showQuestions(questionDataArray: ArrayList<QuestionData>) {

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
            tvOptions.forEach { it.background = ContextCompat.getDrawable(this, R.drawable.option_background); it.textSize = 30f; it.setTypeface(null, Typeface.NORMAL) }
            // todo fix bold text reset
            var finishedSelection = false
            var indexSelected: Int? = null
            tvOptions.forEachIndexed { tvIndex, tvOption ->
                // reset all background colors
                tvOption.backgroundTintList = ColorStateList.valueOf(Color.WHITE)

                // set action listeners
                tvOption.setOnClickListener {
                    if(!finishedSelection) {
                        // save the selected index
                        indexSelected = tvIndex

                        // display the selected option as picked, by increasing text size, adding a tint and make it bold
                        // readjust the other options to have their original size, tint and set the text style to bold
                        tvOptions.forEachIndexed { index, option ->  if(index == indexSelected) {option.textSize = 37f
                            option.setTypeface(tvOption.typeface, Typeface.BOLD)
                            option.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.selectedColor))}
                        else { option.textSize = 30f; option.setTypeface(null, Typeface.NORMAL)
                           option.backgroundTintList = ColorStateList.valueOf(Color.WHITE)}}
                    }
                }
            }
            btnSelect.setOnClickListener {
                // if no option is selected, just notify the user to select an option
                if(indexSelected == null) {
                    Toast.makeText(this, "Please select one of the options.", Toast.LENGTH_SHORT).show()
                }
                else {
                    // prevents any UI changes of the options if pressed after the correct answer is revealed
                    finishedSelection = true

                    // regardless of what is pressed, display the correct answer with a green background
                    tvOptions[questionData.answer.value].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.greenCorrect))

                    //  todo set background to a green or red drawable or think how to change the solid attribute of the drawable
                   // tvOptions[questionData.answer.value].background = resources.getDrawable(R.drawable.newBackground_here)

                    // if the selected option is incorrect, add a red background to it
                    if(questionData.answer.value != indexSelected) {
                        tvOptions[indexSelected!!].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.redWrong))
                    }

                    // adjust progress bar
                    progressBar.progress++
                    tvProgress.text = StringBuilder(progressBar.progress.toString()).append("/").append(progressBar.max)

                    // set the button text depending on whether it is the last question or not
                    btnSelect.text = if(questionDataArray.size == 1) "FINISH" else "CONTINUE" // todo use R class
                    btnSelect.setOnClickListener {

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