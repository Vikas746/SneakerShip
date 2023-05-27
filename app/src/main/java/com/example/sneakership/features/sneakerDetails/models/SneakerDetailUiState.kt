package com.example.sneakership.features.sneakerDetails.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.sneakership.features.home.models.Sneaker

data class SneakerDetailUiState(
    val sneaker: Sneaker = Sneaker(),
    val selectedSize: MutableState<Int> = mutableStateOf(0),
    val selectedColor: MutableState<String> = mutableStateOf(""),
    val imageCnt: Int = 3,
    val currentImagePos: MutableState<Int> = mutableStateOf(1)
)
