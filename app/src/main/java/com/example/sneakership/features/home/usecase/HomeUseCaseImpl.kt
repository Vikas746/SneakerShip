package com.example.sneakership.features.home.usecase

import com.example.sneakership.features.home.repository.SneakersRepository
import javax.inject.Inject

class HomeUseCaseImpl @Inject constructor(private val sneakersRepository: SneakersRepository) :
    HomeUseCase {

    override fun fetchSneakers() = sneakersRepository.fetchSneakers()

}