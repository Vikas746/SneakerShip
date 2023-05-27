package com.example.sneakership.features.home.repository

import com.example.sneakership.features.home.models.Sneaker

interface SneakersRepository {
    fun fetchSneakers(): List<Sneaker>
}