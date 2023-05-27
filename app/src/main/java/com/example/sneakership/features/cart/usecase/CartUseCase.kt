package com.example.sneakership.features.cart.usecase

import com.example.sneakership.features.sneakerDetails.models.SneakerDetail
import kotlinx.coroutines.flow.Flow

interface CartUseCase {
    fun fetchCartItems(): Flow<List<SneakerDetail>>
    suspend fun deleteCartItem(sneakerDetail: SneakerDetail)
}