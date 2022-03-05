package com.fueled.composechallenge.ui.components.quiz

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import coil.compose.rememberImagePainter
import com.fueled.composechallenge.R
import com.fueled.composechallenge.model.Answer
import com.fueled.composechallenge.model.QuizItem
import com.fueled.composechallenge.ui.anim.AnswerAnimation
import com.fueled.composechallenge.ui.components.QuestionBox
import com.fueled.composechallenge.ui.components.RoundedCard
import com.fueled.composechallenge.ui.components.animatedprogress.AnimatedProgressState
import com.fueled.composechallenge.ui.components.answerslider.AnswerSlider
import com.fueled.composechallenge.ui.components.answerslider.AnswerSliderState
import com.fueled.composechallenge.ui.theme.Colors
import com.fueled.composechallenge.ui.theme.Dimens
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun QuizContainer(
    quizItem: QuizItem,
    progressState: AnimatedProgressState,
    modifier: Modifier = Modifier,
    onStartTimer: () -> Unit = {},
    onStopTimer: (Answer?) -> Unit = {}
) {
    val maxWidth =
        if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) 0.45F else 0.8F
    val question = quizItem.question
    val user = question.user
    val answers = question.answers
    var isAnswersVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(ANIM_DELAY)
        isAnswersVisible = true
        onStartTimer()
    }

    Column(
        modifier = modifier
            .fillMaxWidth(maxWidth)
            .padding(top = Dimens.PaddingHalf)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            RoundedCard(
                modifier = Modifier.fillMaxWidth(0.4F),
                bodyText = user.name,
                imageUrl = user.imageUrl,
                iconBgColor = Color.White
            )
            Image(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable { onStopTimer(null) }
                    .align(Alignment.CenterEnd)
                    .size(Dimens.ImageCircleSize)
                    .background(Colors.BgAnswerCard)
                    .padding(Dimens.PaddingHalf),

                painter = rememberImagePainter(R.drawable.ic_close),
                colorFilter = ColorFilter.tint(Colors.TextColor),
                contentDescription = stringResource(id = R.string.content_desc_close)
            )
        }

        Spacer(modifier = Modifier.height(Dimens.PaddingHalf))

        QuestionBox(
            progressState = progressState,
            questionText = quizItem.question.questionText,
            timerDuration = quizItem.question.duration,
            onTimerComplete = { onStopTimer(null) }
        )
        answers.mapIndexed { index, answer ->
            Spacer(modifier = Modifier.height(Dimens.PaddingHalf))
            val isAnswered = quizItem.selectedAnswer == answer
            AnswerAnimation(isVisible = isAnswersVisible, offset = ANSWER_OFFSET * index) {
                AnswerSlider(
                    answer = answer,
                    answerSliderState = if (isAnswered) AnswerSliderState.SELECTED else AnswerSliderState.UNSELECTED,
                    onAnswerClicked = if (quizItem.isActive) {
                        { selectedAnswer -> onStopTimer(selectedAnswer) }
                    } else null
                )
            }
        }
    }
}

private const val ANSWER_OFFSET = 200
private const val ANIM_DELAY = 500L
