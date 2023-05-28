package com.example.sneakership.features.cart.usecase

import com.example.sneakership.features.sneakerDetails.models.SneakerDetail
import kotlinx.coroutines.flow.Flow

interface CartUseCase {

    /**
     * This method fetches the sneakers from database.
     */
    fun fetchCartItems(): Flow<List<SneakerDetail>>

    /**
     * This method deletes the sneaker from database.
     */
    suspend fun deleteCartItem(sneakerDetail: SneakerDetail)
}