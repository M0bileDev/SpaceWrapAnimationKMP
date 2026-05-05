package com.example.spacewrapanimationkmp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.spacewrapanimationkmp.components.Space
import com.example.spacewrapanimationkmp.model.SpaceRepresentation
import org.jetbrains.compose.resources.painterResource

import spacewrapanimationkmp.composeapp.generated.resources.Res
import spacewrapanimationkmp.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        Space(
            modifier = Modifier.fillMaxSize(),
            spaceRepresentation = SpaceRepresentation.LightStreak()
        )
    }
}