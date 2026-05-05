package com.example.spacewrapanimationkmp.shapes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LightStreakShape(
    degrees: Float = 0f,
    rotation: Float = 0f,
    scale: Float = 1f,
    offset: Float = 1f,
    color: Color = Color.Yellow,
    cornerShapeSize: Dp = 8.dp,
    width: Dp = 10.dp,
    height: Dp = 40.dp,
    modifier: Modifier = Modifier
) {

    val degrees by remember { mutableStateOf(degrees) }
    val color by remember { mutableStateOf(color) }

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
        Box(
            modifier = Modifier
                .background(
                    color = color.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(cornerShapeSize)
                )
                .size(width = width, height = height),
        )
        Box(
            modifier = Modifier
                .background(color = color, shape = RoundedCornerShape(cornerShapeSize))
                .size(width = width, height = height),
        )
        Box(
            modifier = Modifier
                .background(
                    color = color.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(cornerShapeSize)
                )
                .size(width = width, height = height),
        )
    }
}

@Preview
@Composable
fun PreviewLightStreakShape() {
    LightStreakShape()
}
