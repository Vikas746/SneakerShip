package com.example.sneakership.features.sneakerDetails.di

import com.example.sneakership.features.sneakerDetails.repository.SneakerDetailRepository
import com.example.sneakership.features.sneakerDetails.repository.SneakerDetailRepositoryImpl
import com.example.sneakership.features.sneakerDetails.usecase.SneakerDetailUseCase
import com.example.sneakership.features.sneakerDetails.usecase.SneakerDetailsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SneakerDetailModule {

    @Binds
    abstract fun bindSneakerDetailUseCase(useCaseImpl: SneakerDetailsUseCaseImpl): SneakerDetailUseCase

    @Binds
    abstract fun bindSneakerDetailRepository(
        repositoryImpl: SneakerDetailRepositoryImpl
    ): SneakerDetailRepository
}