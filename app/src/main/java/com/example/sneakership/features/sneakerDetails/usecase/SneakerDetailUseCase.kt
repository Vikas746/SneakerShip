package com.example.sneakership.features.sneakerDetails.usecase

import com.example.sneakership.features.home.models.Sneaker

interface SneakerDetailUseCase {

    /**
     * This method adds the sneaker to database.
     */
    suspend fun addToCart(sneaker: Sneaker, selectedSize: Int, selectedColor: String)
}