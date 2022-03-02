package com.fueled.composechallenge.ui.components.answerslider

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.fueled.composechallenge.model.Answer
import com.fueled.composechallenge.ui.components.RoundedCard
import com.fueled.composechallenge.ui.theme.Colors
import com.fueled.composechallenge.ui.theme.Dimens

@Suppress("TransitionPropertiesLabel")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnswerSlider(
    modifier: Modifier = Modifier,
    answerSliderState: AnswerSliderState = AnswerSliderState.IDLE,
    answer: Answer,
    onAnswerClicked: ((Answer) -> Unit)? = null
) {
    val transition = updateTransition(targetState = answerSliderState, label = "slider_state")
    val bgColor by transition.animateColor({ tween(TRANSITION_DURATION) }) { targetState ->
        when (targetState) {
            AnswerSliderState.IDLE -> Colors.BgAnswerCard
            AnswerSliderState.ANSWERED -> if (answer.isCorrect) Colors.Success else Colors.Error
        }
    }
    val textColor by transition.animateColor({ tween(TRANSITION_DURATION) }) { targetState ->
        when (targetState) {
            AnswerSliderState.IDLE -> Colors.TextColor
            AnswerSliderState.ANSWERED -> Color.White
        }
    }
    val iconAlignment by transition.animateFloat({ tween(TRANSITION_DURATION) }) { targetState ->
        when (targetState) {
            AnswerSliderState.IDLE -> -1F
            AnswerSliderState.ANSWERED -> 1F
        }
    }
    val textOffset by transition.animateDp({ tween(TRANSITION_DURATION) }) { targetState ->
        when (targetState) {
            AnswerSliderState.IDLE -> Dimens.AnswerTextOffset
            AnswerSliderState.ANSWERED -> Dimens.PaddingHalf
        }
    }

    RoundedCard(
        modifier = modifier.fillMaxWidth(0.6F),
        iconText = answer.option.replaceFirstChar { it.titlecase() },
        iconHorizontalAlignment = iconAlignment,
        bodyText = answer.answerText,
        bodyTextColor = textColor,
        bodyTextOffset = textOffset,
        bgColor = bgColor,
        onClicked = if (onAnswerClicked != null) {
            { onAnswerClicked(answer) }
        } else null
    )
}

private const val TRANSITION_DURATION = 1000