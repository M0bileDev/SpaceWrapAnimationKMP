package com.example.spacewrapanimationkmp.shapes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LightStreakShape(
    degrees: Float = 0f,
    rotation: Float = 0f,
    scale: Float = 1f,
    offset: Float = 1f,
    color: Color = Color.Yellow,
    cornerShapeSize: Dp = 0.dp,
    width: Dp = 10.dp,
    height: Dp = 40.dp,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .rotate(degrees)
            .graphicsLayer {
                rotationX = rotation
                scaleX = scale
                scaleY = scale
                translationY = offset
            }
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

    }
}
