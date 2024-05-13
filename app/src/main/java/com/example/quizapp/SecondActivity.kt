package com.example.quizapp

import RecyclerViewAdapter
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.StringBuilder

class SecondActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val userName = intent.getStringExtra("userName") // value passed from MainActivity
        // set the TextView with the username that the user typed in the field from the MainActivity
        findViewById<TextView>(R.id.tvHello).text = StringBuilder(getString(R.string.hello))
            .append(userName).append(getString(R.string.exclamation))

        // initialize the data list for displaying content in the quiz previews of the ListView elements
        val dataList: List<QuizPreviewData> = listOf(
            QuizPreviewData("Classical No. 1", "Description 1 djfksjdkfljdkjfklsjdkfjsdfjdjk", R.drawable.classical_1),
            QuizPreviewData("Title 2", "Description 2", R.drawable.ic_launcher_background),
            QuizPreviewData("Title 3", "Description 3", R.drawable.ic_launcher_background),
            QuizPreviewData("Title 4", "Description 4", R.drawable.ic_launcher_background),
            QuizPreviewData("Title 5", "Description 5", R.drawable.ic_launcher_background),
            QuizPreviewData("Title 6", "Description 6", R.drawable.ic_launcher_background),
            QuizPreviewData("Title 7", "Description 7", R.drawable.ic_launcher_background),
            QuizPreviewData("Title 8", "Description 8", R.drawable.ic_launcher_background),
            QuizPreviewData("Title 9", "Description 9", R.drawable.ic_launcher_background),
            QuizPreviewData("Title 10", "Description 10", R.drawable.ic_launcher_background),
            QuizPreviewData("Title 11", "Description 11", R.drawable.ic_launcher_background))

        val recyclerViewAdapter = RecyclerViewAdapter(this@SecondActivity, dataList)

        // FrameLayout in the xml file is a container for the RecyclerView
        val container = findViewById<FrameLayout>(R.id.frameLayout)
        val recyclerView = RecyclerView(this)
        recyclerView.adapter = recyclerViewAdapter
        // set height and width
        recyclerView.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)

        // set layout manager
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.isNestedScrollingEnabled = false

        // add recyclerView to the container (FrameLayout)
        container.addView(recyclerView)


    }
    fun showQuizPreviewDialog(position: Int, title: CharSequence, description: CharSequence) {
        val dialog = Dialog(this)

        dialog.setContentView(R.layout.dialog_preview_layout)
        dialog.findViewById<TextView>(R.id.tvTitle).text = title
        dialog.findViewById<TextView>(R.id.tvDescription).text = description
        dialog.findViewById<Button>(R.id.startButton).setOnClickListener {
            startQuizActivity(position)
        }
        dialog.window?.setBackgroundDrawableResource(R.drawable.quiz_preview_background)
        dialog.setCanceledOnTouchOutside(true)
        dialog.show()
    }

    fun startQuizActivity(position: Int) {

    }

}



