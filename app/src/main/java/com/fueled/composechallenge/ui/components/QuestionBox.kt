package com.fueled.composechallenge.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.constraintlayout.compose.ConstraintLayout
import com.fueled.composechallenge.ui.components.animatedprogress.AnimatedProgressIndicator
import com.fueled.composechallenge.ui.components.animatedprogress.AnimatedProgressState
import com.fueled.composechallenge.ui.theme.Colors
import com.fueled.composechallenge.ui.theme.Dimens
import java.time.Duration

@Composable
fun QuestionBox(
    modifier: Modifier = Modifier,
    progressState: AnimatedProgressState,
    questionText: String = "",
    timerDuration: Duration = Duration.ofSeconds(10L),
    onTimerComplete: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(Dimens.QuestionBoxCornerRadius))
            .background(Colors.BgCard)
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (_, progressIndicator) = createRefs()
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.8F)
                    .padding(Dimens.PaddingDefault),
                text = questionText,
                style = MaterialTheme.typography.h1
            )

            AnimatedProgressIndicator(
                modifier = Modifier.constrainAs(progressIndicator) {
                    end.linkTo(parent.end, Dimens.PaddingHalf)
                    top.linkTo(parent.top, Dimens.PaddingHalf)
                },
                duration = timerDuration,
                state = progressState,
                timerFormat = "s",
                onTimerComplete = onTimerComplete
            )
        }
    }
}
