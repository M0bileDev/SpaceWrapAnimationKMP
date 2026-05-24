package com.example.spacewrapanimationkmp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.spacewrapanimationkmp.animations.LightStreakAnimation
import com.example.spacewrapanimationkmp.model.SpaceRepresentation
import com.example.spacewrapanimationkmp.shapes.LightStreakShape
import kotlin.random.Random

@Composable
fun Space(
    spaceRepresentation: SpaceRepresentation,
    spaceBackground: Color = Color.Black,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(spaceBackground)
    ) {
        when (spaceRepresentation) {
            is SpaceRepresentation.LightStreak -> {
                with(spaceRepresentation) {
                    val delayPerItems = durationMillis / lightStreakCount
                    repeat(lightStreakGroupCount) {
                        repeat(lightStreakCount) { index ->
                            val streakDelay = remember { index * delayPerItems + (1..100).random() }
                            val streakScale = remember { scaleRange.random().toFloat() }
                            val streakColor = remember { colors.random() }
                            val streakDegrees = remember { Random.nextFloat() * 360f }
                            LightStreakAnimation(
                                delayMillis = streakDelay,
                                durationMillis = durationMillis,
                                scale = streakScale,
                            ) { scale, progress ->
                                LightStreakShape(
                                    color = streakColor,
                                    degrees = streakDegrees,
                                    scale = scale,
                                    progress = progress,
                                    rotation = rotation,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
