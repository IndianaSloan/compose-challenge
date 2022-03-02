package com.fueled.composechallenge.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import coil.compose.rememberImagePainter
import com.fueled.composechallenge.ui.components.Modifiers.content
import com.fueled.composechallenge.ui.components.Modifiers.icon
import com.fueled.composechallenge.ui.theme.Colors
import com.fueled.composechallenge.ui.theme.Dimens

@Composable
fun RoundedCard(
    modifier: Modifier,
    onClicked: (() -> Unit)? = null,
    bgColor: Color = Colors.BgAnswerCard,
    iconHorizontalAlignment: Float = -1.0F,
    iconText: String? = null,
    iconImageRes: Int = -1,
    imageUrl: String? = null,
    iconBgColor: Color = Colors.TextColor,
    bodyText: String,
    bodyTextColor: Color = Colors.TextColor,
    bodyTextOffset: Dp = Dimens.AnswerTextOffset
) {
    Box(
        modifier = modifier.content(bgColor, onClicked),
        contentAlignment = Alignment.CenterStart,
    ) {
        Box(
            modifier = icon(iconHorizontalAlignment, iconBgColor),
            contentAlignment = Alignment.Center
        ) {
            when {
                iconText != null -> {
                    Text(
                        textAlign = TextAlign.Center,
                        text = iconText,
                        color = Color.White,
                        style = MaterialTheme.typography.h1,
                    )
                }
                iconImageRes != -1 -> {
                    Image(
                        painter = painterResource(id = iconImageRes),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(Dimens.PaddingEighth)
                            .clip(CircleShape)
                    )
                }
                imageUrl != null -> {
                    Image(
                        painter = rememberImagePainter(imageUrl),
                        contentScale = ContentScale.Crop,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(Dimens.PaddingEighth)
                            .clip(CircleShape)
                    )
                }
            }
        }
        Text(
            text = bodyText,
            modifier = Modifier
                .padding(start = Dimens.PaddingHalf)
                .absoluteOffset(x = bodyTextOffset),
            style = MaterialTheme.typography.h1,
            color = bodyTextColor,
        )
    }
}

private object Modifiers {

    fun Modifier.content(bgColor: Color, onClicked: (() -> Unit)?): Modifier {
        val innerModifier = this
            .height(Dimens.AnswerBoxHeight)
            .clip(RoundedCornerShape(Dimens.AnswerBoxCornerRadius))
            .background(bgColor)

        return if (onClicked != null) {
            innerModifier.clickable { onClicked() }
        } else innerModifier
    }

    fun BoxScope.icon(iconHorizontalAlignment: Float, iconBgColor: Color): Modifier {
        return Modifier
            .align(BiasAlignment(iconHorizontalAlignment, 0F))
            .padding(start = Dimens.PaddingFourth, end = Dimens.PaddingFourth)
            .clip(CircleShape)
            .size(Dimens.ImageCircleSize)
            .background(iconBgColor)
    }
}
