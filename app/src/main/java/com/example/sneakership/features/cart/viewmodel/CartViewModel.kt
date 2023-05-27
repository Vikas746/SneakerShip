package com.example.sneakership.features.cart.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakership.features.cart.models.CartUiState
import com.example.sneakership.features.cart.usecase.CartUseCase
import com.example.sneakership.features.sneakerDetails.models.SneakerDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val cartUseCase: CartUseCase) : ViewModel() {

    var cartUiState: CartUiState by mutableStateOf(CartUiState())

    init {
        fetchCartItems()
    }

    private fun fetchCartItems() {
        viewModelScope.launch {
            cartUseCase.fetchCartItems().collectLatest {
                val subTotal = calculateSubTotal(it)
                val tax: Int = (0.05 * subTotal).toInt()
                cartUiState = cartUiState.copy(
                    cartItems = it,
                    subTotal = subTotal,
                    tax = tax,
                    total = subTotal + tax
                )
            }
        }
    }

    private fun calculateSubTotal(cartItems: List<SneakerDetail>): Int {
        var subTotal = 0
        cartItems.forEach { subTotal += it.price }
        return subTotal
    }

    fun deleteCartItem(sneakerDetail: SneakerDetail) {
        viewModelScope.launch {
            cartUseCase.deleteCartItem(sneakerDetail)
        }
    }
}