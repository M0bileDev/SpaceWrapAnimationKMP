package com.example.spacewrapanimationkmp.animations

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.spacewrapanimationkmp.shapes.LightStreakShape

typealias Scale = Float
typealias Progress = Float

@Composable
fun LightStreakAnimation(
    scale: Float = 1f,
    durationMillis: Int = 0,
    delayMillis: Int = 0,
    onUpdate: @Composable (Scale, Progress) -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "LightStreak")
    val spec = remember(durationMillis, delayMillis) {
        infiniteRepeatable<Float>(
            animation = tween(durationMillis = durationMillis),
            repeatMode = RepeatMode.Restart,
            initialStartOffset = StartOffset(delayMillis)
        )
    }
    val animatedScale by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = scale,
        animationSpec = spec,
        label = "LightStreakScale"
    )
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = spec,
        label = "LightStreakProgress"
    )
    onUpdate(animatedScale, progress)
}

@Preview(backgroundColor = 0xFF000000, showBackground = true, widthDp = 1080, heightDp = 1920)
@Composable
fun PreviewLightStreakAnimationPortrait() {
    LightStreakAnimation(
        scale = 3f,
        durationMillis = 1000
    ) { scale, progress ->
        LightStreakShape(
            degrees = 45f,
            scale = scale,
            progress = progress,
            rotation = -70f,
        )
    }
}

@Preview(backgroundColor = 0xFF000000, showBackground = true, widthDp = 1920, heightDp = 1080)
@Composable
fun PreviewLightStreakAnimationLandscape() {
    LightStreakAnimation(
        scale = 3f,
        durationMillis = 1000
    ) { scale, progress ->
        LightStreakShape(
            degrees = 45f,
            scale = scale,
            progress = progress,
            rotation = -70f,
        )
    }
}
