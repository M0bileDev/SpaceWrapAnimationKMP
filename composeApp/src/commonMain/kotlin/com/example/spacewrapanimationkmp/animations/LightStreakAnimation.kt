package com.example.spacewrapanimationkmp.animations

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

@Composable
fun LightStreakAnimation(
    scale: Float = 1f,
    durationMillis: Int = 0,
    delayMillis: Int = 0,
    offset: Float = 1f,
    onUpdate: @Composable (Float, Float) -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = scale,
        animationSpec = infiniteRepeatable(
            tween(
                delayMillis = delayMillis,
                durationMillis = durationMillis
            ), RepeatMode.Restart
        )
    )
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = offset,
        animationSpec = infiniteRepeatable(
            tween(
                delayMillis = delayMillis,
                durationMillis = durationMillis
            ), RepeatMode.Restart
        )
    )
    onUpdate(scale, offset)
}