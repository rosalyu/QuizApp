package com.example.quizapp

import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.StringBuilder

class SecondActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val listView = findViewById<ListView>(R.id.listView)

        val userName = intent.getStringExtra("userName") // value passed from MainActivity
        // set the TextView with the username that the user typed in the field from the MainActivity
        findViewById<TextView>(R.id.tvHello).text = StringBuilder(getString(R.string.hello))
            .append(userName).append(getString(R.string.exclamation))

        // initialize the data list for displaying content in the quiz previews of the ListView elements
        val dataList: List<ListItemData> = listOf(
            ListItemData(
                QuizPreviewData("Title 1", "Description 1", R.drawable.ic_launcher_background),
                QuizPreviewData("Title 2", "Description 2", R.drawable.ic_launcher_background)),

            ListItemData(
                QuizPreviewData("Title 3", "Description 3", R.drawable.ic_launcher_background),
                QuizPreviewData("Title 4", "Description 4", R.drawable.ic_launcher_background)),

            ListItemData(
                QuizPreviewData("Title 5", "Description 5", R.drawable.ic_launcher_background),
                QuizPreviewData("Title 6", "Description 6", R.drawable.ic_launcher_background)),

            ListItemData(
                QuizPreviewData("Title 7", "Description 7", R.drawable.ic_launcher_background),
                QuizPreviewData("Title 8", "Description 8", R.drawable.ic_launcher_background)),

            ListItemData(
                QuizPreviewData("Title 9", "Description 9", R.drawable.ic_launcher_background),
                QuizPreviewData("Title 10", "Description 10", R.drawable.ic_launcher_background))
        )
        val listAdapter = ListAdapter(this, R.layout.list_element_layout, dataList)
        listView.adapter = listAdapter


    }
}