package com.example.sneakership.features.home.models

import androidx.compose.ui.graphics.Color

data class Sneaker(
    val name: String,
    val brand: String,
    val price: Int,
    val sizes: List<Int>,
    val colors: List<Color>
)
