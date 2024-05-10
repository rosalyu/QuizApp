package com.example.quizapp

import RecyclerViewAdapter
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.StringBuilder

class SecondActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        //val listView = findViewById<ListView>(R.id.listView)

        val userName = intent.getStringExtra("userName") // value passed from MainActivity
        // set the TextView with the username that the user typed in the field from the MainActivity
        findViewById<TextView>(R.id.tvHello).text = StringBuilder(getString(R.string.hello))
            .append(userName).append(getString(R.string.exclamation))

        // initialize the data list for displaying content in the quiz previews of the ListView elements
        val dataList: List<QuizPreviewData> = listOf(
            QuizPreviewData("Title 1", "Description 1", R.drawable.ic_launcher_background),
            QuizPreviewData("Title 2", "Description 2", R.drawable.ic_launcher_background),
            QuizPreviewData("Title 3", "Description 3", R.drawable.ic_launcher_background),
            QuizPreviewData("Title 4", "Description 4", R.drawable.ic_launcher_background),
            QuizPreviewData("Title 5", "Description 5", R.drawable.ic_launcher_background),
            QuizPreviewData("Title 6", "Description 6", R.drawable.ic_launcher_background),
            QuizPreviewData("Title 7", "Description 7", R.drawable.ic_launcher_background),
            QuizPreviewData("Title 8", "Description 8", R.drawable.ic_launcher_background),
            QuizPreviewData("Title 9", "Description 9", R.drawable.ic_launcher_background),
            QuizPreviewData("Title 10", "Description 10", R.drawable.ic_launcher_background),
            QuizPreviewData("Title 6", "Description 6", R.drawable.ic_launcher_background),
            QuizPreviewData("Title 7", "Description 7", R.drawable.ic_launcher_background),
            QuizPreviewData("Title 8", "Description 8", R.drawable.ic_launcher_background),
            QuizPreviewData("Title 9", "Description 9", R.drawable.ic_launcher_background),
            QuizPreviewData("Title 10", "Description 10", R.drawable.ic_launcher_background))
        //val listAdapter = ListAdapter(this, R.layout.list_element_layout, dataList)
        //listView.adapter = listAdapter
        val recyclerViewAdapter = RecyclerViewAdapter(dataList)

        // FrameLayout in the xml file is a container for the RecyclerView
        val container = findViewById<FrameLayout>(R.id.listView)
        val recyclerView = RecyclerView(this)
        recyclerView.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        // set layout manager
        // todo think of using GridLayout
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.isNestedScrollingEnabled = false
        // make spacings using decoration class

        // add recyclerView to the container (FrameLayout)
        container.addView(recyclerView)


    }
}