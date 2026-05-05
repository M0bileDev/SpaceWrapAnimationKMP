package com.example.spacewrapanimationkmp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalWindowInfo

enum class Orientation { Portrait, Landscape }

@Composable
fun currentOrientation(): Orientation {
    val size = LocalWindowInfo.current.containerSize
    return if (size.height > size.width) Orientation.Portrait else Orientation.Landscape
}