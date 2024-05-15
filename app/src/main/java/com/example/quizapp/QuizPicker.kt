package com.example.quizapp

// contains all questions for a quiz
class QuizPicker(position: Int) {
    val selected = when(position) {
        0 -> QuizData(arrayListOf(QuestionData("Who composed this piece?", "Brahms", "Tchaikovsky", "Dvorak", "Mendelssohn", Option.ONE),
            QuestionData("Who composed this piece?", "Mozart", "Bach", "Haydn", "Schubert", Option.TWO)))
        1 -> QuizData(arrayListOf(QuestionData("Question 0", "option1", "option2", "option3", "option4", Option.TWO)))
        2 -> QuizData(arrayListOf(QuestionData("Question 0", "option1", "option2", "option3", "option4", Option.THREE)))
        3 -> QuizData(arrayListOf(QuestionData("Question 0", "option1", "option2", "option3", "option4", Option.FOUR)))
        4 -> QuizData(arrayListOf(QuestionData("Question 0", "option1", "option2", "option3", "option4", Option.ONE)))
        5 -> QuizData(arrayListOf(QuestionData("Question 0", "option1", "option2", "option3", "option4", Option.TWO)))
        6 -> QuizData(arrayListOf(QuestionData("Question 0", "option1", "option2", "option3", "option4", Option.THREE)))
        7 -> QuizData(arrayListOf(QuestionData("Question 0", "option1", "option2", "option3", "option4", Option.FOUR)))
        8 -> QuizData(arrayListOf(QuestionData("Question 0", "option1", "option2", "option3", "option4", Option.ONE)))
        9 -> QuizData(arrayListOf(QuestionData("Question 0", "option1", "option2", "option3", "option4", Option.TWO)))
        10 -> QuizData(arrayListOf(QuestionData("Question 0", "option1", "option2", "option3", "option4", Option.THREE)))
        11 -> QuizData(arrayListOf(QuestionData("Question 0", "option1", "option2", "option3", "option44", Option.FOUR)))

        else -> null
    }
}