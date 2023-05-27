package com.example.sneakership.features.home.usecase

import com.example.sneakership.features.home.models.Sneaker

interface HomeUseCase {
    fun fetchSneakers(): List<Sneaker>
}