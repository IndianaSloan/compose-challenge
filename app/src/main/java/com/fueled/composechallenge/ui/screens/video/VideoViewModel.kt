package com.fueled.composechallenge.ui.screens.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fueled.composechallenge.data.QuizRepository
import com.fueled.composechallenge.model.Answer
import com.fueled.composechallenge.model.QuizItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class VideoViewModel : ViewModel() {

    private val repository: QuizRepository = QuizRepository()

    private val _uiState = MutableStateFlow(VideoUIState())
    val uiState: StateFlow<VideoUIState> = _uiState

    init {
        collectQuestionsStream()
    }

    private fun collectQuestionsStream() {
        viewModelScope.launch {
            delay(DELAY)
            repository.questionFlow.collect { quizItem ->
                _uiState.value = uiState.value.copy(quizItem = quizItem, isVisible = true)
            }
        }
    }

    fun startTimer() {
        val updatedQuizItem = uiState.value.quizItem?.copy(isActive = true)
        _uiState.value = uiState.value.copy(quizItem = updatedQuizItem)
    }

    fun stopTimer(selectedAnswer: Answer? = null) {
        val updatedQuizItem =
            uiState.value.quizItem?.copy(selectedAnswer = selectedAnswer, isActive = false)
        _uiState.value = uiState.value.copy(quizItem = updatedQuizItem)
        hideQuizAndTriggerNextQuestion(selectedAnswer != null)
    }

    private fun hideQuizAndTriggerNextQuestion(addDelay: Boolean) {
        viewModelScope.launch {
            if (addDelay) {
                delay(DELAY)
            }
            _uiState.value = uiState.value.copy(isVisible = false)
            delay(DELAY)
            getNextQuestion()
        }
    }

    private fun getNextQuestion() {
        repository.getNextQuestion()
    }
}

data class VideoUIState(
    val quizItem: QuizItem? = null,
    val isVisible: Boolean = false
)

private const val DELAY = 2000L
