package com.fueled.composechallenge.model

data class QuizItem(
    val question: Question = Question.demoQuestions().first(),
    val selectedAnswer: Answer? = null,
    val isActive: Boolean = false,
)
