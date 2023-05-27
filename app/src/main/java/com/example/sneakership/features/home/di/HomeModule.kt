package com.example.sneakership.features.home.di

import com.example.sneakership.features.home.repository.SneakersRepository
import com.example.sneakership.features.home.repository.SneakersRepositoryImpl
import com.example.sneakership.features.home.usecase.HomeUseCase
import com.example.sneakership.features.home.usecase.HomeUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeModule {

    @Binds
    abstract fun bindUseCase(useCase: HomeUseCaseImpl): HomeUseCase

    @Binds
    abstract fun bindRepository(repository: SneakersRepositoryImpl): SneakersRepository
}