package com.fueled.composechallenge.ui.components.animatedprogress

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import com.fueled.composechallenge.ui.theme.Colors
import com.fueled.composechallenge.ui.theme.Dimens
import com.fueled.composechallenge.ui.theme.Typography
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.Calendar
import java.util.Locale

@Composable
fun AnimatedProgressIndicator(
    modifier: Modifier = Modifier,
    state: AnimatedProgressState,
    duration: Duration,
    timerFormat: String = "m:ss",
    size: Dp = Dimens.ProgressIndicatorSize,
    strokeColor: Color = Colors.Red,
    strokeWidth: Dp = Dimens.ProgressIndicatorStroke,
    trackColor: Color = Colors.BgTimer,
    onTimerComplete: () -> Unit = {},
) {
    val animatedProgress by animateFloatAsState(
        targetValue = state.progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )

    LaunchedEffect(state.isRunning) {
        withContext(Dispatchers.Default) {
            while (state.isRunning) {
                val countdownValue =
                    duration.toMillis() - (duration.toMillis() * state.progress).toInt()
                state.progress += INTERVAL.toFloat() / duration.toMillis().toFloat()
                val c = Calendar.getInstance().apply { timeInMillis = countdownValue }
                val formatter = SimpleDateFormat(timerFormat, Locale.getDefault())
                state.text = formatter.format(c.time)
                state.isRunning = state.progress < 1F
                if (!state.isRunning) {
                    onTimerComplete()
                }
                delay(INTERVAL)
            }
        }
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(
            text = state.text,
            style = Typography.h1
        )
        Canvas(modifier = Modifier.size(size)) {
            drawArc(
                color = trackColor,
                startAngle = 0F,
                sweepAngle = SWEEP_ANGLE,
                useCenter = false,
                style = Stroke(strokeWidth.toPx())
            )
            drawArc(
                color = strokeColor,
                startAngle = -90F,
                sweepAngle = SWEEP_ANGLE * animatedProgress,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
    }
}

private const val INTERVAL = 100L
private const val SWEEP_ANGLE = 360F
