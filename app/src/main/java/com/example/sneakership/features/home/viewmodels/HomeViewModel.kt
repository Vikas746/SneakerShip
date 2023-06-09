package com.example.sneakership.features.home.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.sneakership.features.home.models.HomeToolbarState
import com.example.sneakership.features.home.models.HomeUiState
import com.example.sneakership.features.home.models.Sneaker
import com.example.sneakership.features.home.models.SortOptions
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

    /**
     * This method fetches sneakers data.
     */
    private fun fetchSneakers() {
        sneakers.addAll(useCase.fetchSneakers())
        homeUiState = homeUiState.copy(sneakers = getSortedList(sneakers))
    }

    /**
     * This method searches for sneaker based on the input search text.
     */
    fun search() {
        val searchText = homeUiState.searchText.value
        if (searchText.isNotEmpty()) {
            homeUiState =
                homeUiState.copy(sneakers = getSortedList(sneakers.filter {
                    it.name.contains(
                        searchText,
                        true
                    )
                }))
        }
    }

    /**
     * This method clears the search text and shows the title in app bar of home screen.
     */
    fun onSearchCleared() {
        homeUiState.toolbarState.value = HomeToolbarState.TITLE
        homeUiState.searchText.value = ""
        homeUiState = homeUiState.copy(sneakers = getSortedList(sneakers))
    }

    /**
     * This method initiates the sort and updates the ui state.
     */
    fun sort() {
        homeUiState = homeUiState.copy(sneakers = getSortedList(homeUiState.sneakers))
    }

    /**
     * This method provides the sorted sneakers list based on sort option selection.
     */
    private fun getSortedList(sneakers: List<Sneaker>): List<Sneaker> {
        return when (homeUiState.sortOption.value) {
            SortOptions.A_TO_Z -> sneakers.sortedBy { it.name }
            SortOptions.Z_TO_A -> sneakers.sortedByDescending { it.name }
            SortOptions.PRICE_LOW_TO_HIGH -> sneakers.sortedBy { it.price }
            SortOptions.PRICE_HIGH_TO_LOW -> sneakers.sortedByDescending { it.price }
        }
    }
}