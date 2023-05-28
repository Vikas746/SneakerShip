package com.example.sneakership.features.home.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class HomeUiState(
    val sneakers: List<Sneaker> = listOf(),
    val toolbarState: MutableState<HomeToolbarState> = mutableStateOf(HomeToolbarState.TITLE),
    val searchText: MutableState<String> = mutableStateOf("")
)
