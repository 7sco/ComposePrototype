package com.isco.android.composeprototype

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

fun randomColor(max: Int = 90): Color {
    val base = 255
    val min = 0

    val finalRed = base - Random.nextInt(min, max)
    val finalGreen = base - Random.nextInt(min, max)
    val finalBlue = base - Random.nextInt(min, max)

    return Color(finalRed, finalGreen, finalBlue)
}