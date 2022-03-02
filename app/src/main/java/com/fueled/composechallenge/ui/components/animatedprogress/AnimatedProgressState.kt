package com.fueled.composechallenge.ui.components.animatedprogress

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

class AnimatedProgressState(progressPercent: Float, text: String, isRunning: Boolean) {
    var progress by mutableStateOf(progressPercent)
    var text by mutableStateOf(text)
    var isRunning by mutableStateOf(isRunning)

    companion object {
        val Saver: Saver<AnimatedProgressState, *> = listSaver(
            save = { state ->
                listOf(state.progress.toString(), state.text, state.isRunning.toString())
            },
            restore = { state ->
                AnimatedProgressState(state[0].toFloat(), state[1], state[2].toBoolean())
            }
        )
    }
}

@Composable
fun rememberAnimatedProgressState(
    progressPercent: Float = 0F,
    text: String = "",
    isRunning: Boolean = true
): AnimatedProgressState {
    return rememberSaveable(progressPercent, text, isRunning, saver = AnimatedProgressState.Saver) {
        AnimatedProgressState(progressPercent, text, isRunning)
    }
}
