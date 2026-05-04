package com.example.spacewrapanimationkmp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    }
}