package com.fueled.composechallenge.ui.anim

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.scaleIn
import androidx.compose.runtime.Composable

@ExperimentalAnimationApi
@Composable
fun AnswerAnimation(isVisible: Boolean, offset: Int, content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = isVisible,
        enter = scaleIn(
            animationSpec = tween(ANIMATION_DURATION, delayMillis = offset)
        ) + expandHorizontally(
            animationSpec = tween(ANIMATION_DURATION, delayMillis = offset)
        )
    ) { content() }
}

private const val ANIMATION_DURATION = 1000
