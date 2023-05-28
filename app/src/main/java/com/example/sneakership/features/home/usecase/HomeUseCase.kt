package com.example.sneakership.features.home.usecase

import com.example.sneakership.features.home.models.Sneaker

interface HomeUseCase {

    /**
     * This method fetches sneakers data.
     */
    fun fetchSneakers(): List<Sneaker>
}