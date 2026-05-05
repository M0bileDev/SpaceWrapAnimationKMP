package com.example.spacewrapanimationkmp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalWindowInfo
import com.example.spacewrapanimationkmp.model.SpaceRepresentation

@Composable
fun Space(
    spaceRepresentation: SpaceRepresentation,
    spaceBackground: Color = Color.Black,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(spaceBackground)
    ) {

        val windowInfo = LocalWindowInfo.current
        val widthPx = windowInfo.containerSize.width
        val heightPx = windowInfo.containerSize.height

        when (spaceRepresentation) {
            is SpaceRepresentation.LightStreak -> {

            }
        }
    }
}