package com.example.sneakership.features.home.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.sneakership.features.home.models.HomeToolbarState
import com.example.sneakership.features.home.models.HomeUiState
import com.example.sneakership.features.home.models.Sneaker
import com.example.sneakership.features.home.usecase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: HomeUseCase) : ViewModel() {

    var homeUiState by mutableStateOf(HomeUiState())
        private set

    private val sneakers = mutableListOf<Sneaker>()

    init {
        fetchSneakers()
    }

    private fun fetchSneakers() {
        sneakers.addAll(useCase.fetchSneakers())
        homeUiState = homeUiState.copy(sneakers = sneakers)
    }

    fun search() {
        val searchText = homeUiState.searchText.value
        if (searchText.isNotEmpty()) {
            homeUiState =
                homeUiState.copy(sneakers = sneakers.filter { it.name.contains(searchText, true) })
        }
    }

    fun onSearchCleared() {
        homeUiState.toolbarState.value = HomeToolbarState.TITLE
        homeUiState.searchText.value = ""
        homeUiState = homeUiState.copy(sneakers = sneakers)
    }
}