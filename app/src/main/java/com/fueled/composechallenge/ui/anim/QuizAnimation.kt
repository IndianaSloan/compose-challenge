package com.fueled.composechallenge.ui.anim

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun QuizAnimation(
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
    content: @Composable () -> Unit = {}
) {
    val targetOffset: Int = -1200
    AnimatedVisibility(
        isVisible,
        modifier = modifier,
        enter = slideInHorizontally(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow,
            )
        ) { targetOffset },
        exit = slideOutHorizontally(
            animationSpec = tween(600, easing = FastOutLinearInEasing)
        ) { targetOffset } + fadeOut(
            animationSpec = tween(delayMillis = 200, easing = FastOutLinearInEasing)
        )
    ) {
        content()
    }
}
