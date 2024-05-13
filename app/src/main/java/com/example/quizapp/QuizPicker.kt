package com.example.quizapp

// contains all questions for a quiz
class QuizPicker(position: Int) {
    val selected = when(position) {
        0 -> QuizData(arrayOf(QuestionData("Question 0")))
        1 -> QuizData(arrayOf(QuestionData("Question 1")))
        2 -> QuizData(arrayOf(QuestionData("Question 2")))
        3 -> QuizData(arrayOf(QuestionData("Question 3")))
        4 -> QuizData(arrayOf(QuestionData("Question 4")))
        5 -> QuizData(arrayOf(QuestionData("Question 5")))
        6 -> QuizData(arrayOf(QuestionData("Question 6")))
        7 -> QuizData(arrayOf(QuestionData("Question 7")))
        8 -> QuizData(arrayOf(QuestionData("Question 8")))
        9 -> QuizData(arrayOf(QuestionData("Question 9")))
        10 -> QuizData(arrayOf(QuestionData("Question 10")))
        11 -> QuizData(arrayOf(QuestionData("Question 11")))

        else -> null
    }
}