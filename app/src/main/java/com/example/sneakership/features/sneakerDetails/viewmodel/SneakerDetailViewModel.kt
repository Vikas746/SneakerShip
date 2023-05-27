package com.example.sneakership.features.sneakerDetails.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.sneakership.features.home.models.Sneaker
import com.example.sneakership.features.sneakerDetails.models.SneakerDetailUiState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SneakerDetailViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle) :
    ViewModel() {

    var sneakerDetailUiState: SneakerDetailUiState by mutableStateOf(SneakerDetailUiState())

    init {
        val sneakerData: String? = savedStateHandle["sneaker"]
        sneakerData?.let {
            val sneaker: Sneaker = Gson().fromJson(it, Sneaker::class.java)
            sneakerDetailUiState = sneakerDetailUiState.copy(sneaker = sneaker)
        }
    }
}