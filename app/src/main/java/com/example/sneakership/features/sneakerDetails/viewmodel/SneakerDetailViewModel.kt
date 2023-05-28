package com.example.sneakership.features.sneakerDetails.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakership.features.home.models.Sneaker
import com.example.sneakership.features.sneakerDetails.models.SneakerDetailUiState
import com.example.sneakership.features.sneakerDetails.usecase.SneakerDetailUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SneakerDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val sneakerDetailUseCase: SneakerDetailUseCase
) :
    ViewModel() {

    var sneakerDetailUiState: SneakerDetailUiState by mutableStateOf(SneakerDetailUiState())
        private set

    /**
     * Here sneaker data is retrieved, which is sent from home screen.
     */
    init {
        val sneakerData: String? = savedStateHandle["sneaker"]
        sneakerData?.let {
            val sneaker: Sneaker = Gson().fromJson(it, Sneaker::class.java)
            sneakerDetailUiState = sneakerDetailUiState.copy(sneaker = sneaker)
        }
    }

    /**
     * This method checks whether all the necessary information is gathered or not, to add a sneaker to cart.
     */
    fun canAddToCart() =
        sneakerDetailUiState.selectedSize.value != 0 && sneakerDetailUiState.selectedColor.value.isNotEmpty()

    /**
     * This method adds the sneaker to database.
     */
    fun addToCart() {
        viewModelScope.launch {
            with(sneakerDetailUiState) {
                sneakerDetailUseCase.addToCart(sneaker, selectedSize.value, selectedColor.value)
                sneakerDetailUiState.addedToCart.value = true
            }
        }
    }

}