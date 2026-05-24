package com.example.spacewrapanimationkmp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalWindowInfo
import com.example.spacewrapanimationkmp.animations.LightStreakAnimation
import com.example.spacewrapanimationkmp.model.SpaceRepresentation
import com.example.spacewrapanimationkmp.shapes.LightStreakShape
import com.example.spacewrapanimationkmp.utils.Orientation
import com.example.spacewrapanimationkmp.utils.currentOrientation
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

        val windowInfo = LocalWindowInfo.current
        val widthPx = windowInfo.containerSize.width
        val heightPx = windowInfo.containerSize.height
        val orientation = currentOrientation()

        when (spaceRepresentation) {
            is SpaceRepresentation.LightStreak -> {
                val computedOffset = when (orientation) {
                    Orientation.Portrait -> {
                        -(widthPx * 3f)
                    }

                    Orientation.Landscape -> {
                        -(heightPx * 3f)
                    }
                }

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
                                offset = computedOffset,
                            ) { scale, offset ->
                                LightStreakShape(
                                    color = streakColor,
                                    degrees = streakDegrees,
                                    scale = scale,
                                    offset = offset,
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