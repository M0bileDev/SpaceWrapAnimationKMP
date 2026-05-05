package com.example.spacewrapanimationkmp.model

import androidx.compose.ui.graphics.Color

interface SpaceRepresentation {
    data class LightStreak(
        val colors: List<Color> = listOf(
            Color.Yellow,
            Color.Blue,
            Color.Red,
            Color.Cyan,
            Color.Magenta,
            Color.Green
        ),
        val durationMillis: Int = 6_000,
        val lightStreakCount: Int = 100,
        val lightStreakGroupCount: Int = 2,
        val lightStreakRotation: Float = -70f,
        val scaleRange: IntRange = 1..6,
        val rotation: Float = -70f
    ) : SpaceRepresentation
}