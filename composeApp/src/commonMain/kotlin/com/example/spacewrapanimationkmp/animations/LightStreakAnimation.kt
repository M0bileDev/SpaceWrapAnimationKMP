package com.example.spacewrapanimationkmp.animations

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.spacewrapanimationkmp.shapes.LightStreakShape

typealias Scale = Float
typealias Offset = Float

@Composable
fun LightStreakAnimation(
    scale: Float = 1f,
    durationMillis: Int = 0,
    delayMillis: Int = 0,
    offset: Float = 1f,
    onUpdate: @Composable (Scale, Offset) -> Unit
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

@Preview(backgroundColor = 0xFF000000, showBackground = true, widthDp = 1080, heightDp = 1920)
@Composable
fun PreviewLightStreakAnimationPortrait() {
    LightStreakAnimation(
        scale = 3f,
        offset = -3360f,
        durationMillis = 1000
    ) { scale, offset ->
        LightStreakShape(
            degrees = 45f,
            scale = scale,
            offset = offset,
            rotation = -70f,
        )
    }
}

@Preview(backgroundColor = 0xFF000000, showBackground = true, widthDp = 1920, heightDp = 1080)
@Composable
fun PreviewLightStreakAnimationLandscape() {
    LightStreakAnimation(
        scale = 3f,
        offset = -3360f,
        durationMillis = 1000
    ) { scale, offset ->
        LightStreakShape(
            degrees = 45f,
            scale = scale,
            offset = offset,
            rotation = -70f,
        )
    }
}