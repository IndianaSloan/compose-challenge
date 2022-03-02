package com.fueled.composechallenge.ui.screens.video

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.fueled.composechallenge.R
import com.fueled.composechallenge.ui.anim.QuizAnimation
import com.fueled.composechallenge.ui.components.animatedprogress.rememberAnimatedProgressState
import com.fueled.composechallenge.ui.components.quiz.QuizContainer
import com.fueled.composechallenge.ui.theme.Dimens
import com.fueled.composechallenge.util.rememberFlowWithLifecycle

@Composable
fun VideoScreen(viewModel: VideoViewModel) {

    val uiState by rememberFlowWithLifecycle(flow = viewModel.uiState)
        .collectAsState(initial = VideoUIState())

    val quizItem = uiState.quizItem

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.img_football_sample),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        QuizAnimation(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = Dimens.PaddingHalf, bottom = Dimens.PaddingHalf),
            isVisible = uiState.isVisible
        ) {
            if (quizItem != null) {
                val progressState = rememberAnimatedProgressState(
                    text = quizItem.question.duration.seconds.toString()
                )
                progressState.isRunning = quizItem.isActive
                QuizContainer(
                    quizItem = quizItem,
                    progressState = progressState,
                    onStartTimer = viewModel::startTimer,
                    onStopTimer = viewModel::stopTimer
                )
            }
        }
    }
}
