package com.example.sneakership.features.home.repository

import com.example.sneakership.features.home.models.Sneaker

interface SneakersRepository {

    /**
     * This method fetches sneakers data.
     */
    fun fetchSneakers(): List<Sneaker>
}