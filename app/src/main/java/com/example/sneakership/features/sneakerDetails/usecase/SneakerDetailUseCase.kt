package com.example.sneakership.features.sneakerDetails.usecase

import com.example.sneakership.features.home.models.Sneaker

interface SneakerDetailUseCase {
    suspend fun addToCart(sneaker: Sneaker, selectedSize: Int, selectedColor: String)
}