package com.example.sneakership.features.cart.usecase

import com.example.sneakership.features.sneakerDetails.models.SneakerDetail
import com.example.sneakership.features.sneakerDetails.repository.SneakerDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartUseCaseImpl @Inject constructor(private val sneakerDetailRepository: SneakerDetailRepository) :
    CartUseCase {
    override fun fetchCartItems(): Flow<List<SneakerDetail>> {
        return sneakerDetailRepository.fetchSneakers()
    }

    override suspend fun deleteCartItem(sneakerDetail: SneakerDetail) {
        sneakerDetailRepository.deleteSneakerDetail(sneakerDetail)
    }
}