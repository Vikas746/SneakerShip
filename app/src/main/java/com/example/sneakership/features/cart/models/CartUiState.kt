package com.example.sneakership.features.cart.models

import com.example.sneakership.features.sneakerDetails.models.SneakerDetail

data class CartUiState(
    val cartItems: List<SneakerDetail> = listOf(),
    val subTotal: Int = 0,
    val tax: Int = 0,
    val total: Int = 0
)