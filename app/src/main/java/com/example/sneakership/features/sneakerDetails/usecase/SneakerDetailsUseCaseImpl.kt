package com.example.sneakership.features.sneakerDetails.usecase

import com.example.sneakership.features.home.models.Sneaker
import com.example.sneakership.features.sneakerDetails.models.SneakerDetail
import com.example.sneakership.features.sneakerDetails.repository.SneakerDetailRepository
import javax.inject.Inject

class SneakerDetailsUseCaseImpl @Inject constructor(private val sneakerDetailRepository: SneakerDetailRepository) :
    SneakerDetailUseCase {

    override suspend fun addToCart(sneaker: Sneaker, selectedSize: Int, selectedColor: String) {
        sneakerDetailRepository.insertSneakerDetail(
            SneakerDetail(
                sneaker.name,
                sneaker.brand,
                selectedSize,
                selectedColor,
                sneaker.price
            )
        )
    }
}