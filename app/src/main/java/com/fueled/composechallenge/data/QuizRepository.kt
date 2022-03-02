package com.fueled.composechallenge.data

import com.fueled.composechallenge.model.Question
import com.fueled.composechallenge.model.QuizItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class QuizRepository(private val dataSource: List<Question> = Question.demoQuestions()) {

    private var currentIndex = 0

    private val _questionFlow = MutableStateFlow(QuizItem())
    val questionFlow: Flow<QuizItem> = _questionFlow

    fun getNextQuestion() {
        if (currentIndex == dataSource.lastIndex) return
        currentIndex++
        _questionFlow.value = QuizItem(dataSource[currentIndex])
    }
}
