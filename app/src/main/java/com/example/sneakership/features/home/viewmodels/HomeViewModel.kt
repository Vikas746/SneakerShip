package com.example.sneakership.features.home.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.sneakership.features.home.models.HomeUiState
import com.example.sneakership.features.home.usecase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: HomeUseCase) : ViewModel() {

    var homeUiState by mutableStateOf(HomeUiState())
        private set

    init {
        fetchSneakers()
    }

    private fun fetchSneakers() {
        homeUiState = homeUiState.copy(sneakers = useCase.fetchSneakers())
    }

}