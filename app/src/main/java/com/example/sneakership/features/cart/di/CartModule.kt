package com.example.sneakership.features.cart.di

import com.example.sneakership.features.cart.usecase.CartUseCase
import com.example.sneakership.features.cart.usecase.CartUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CartModule {

    @Binds
    abstract fun bindCartUseCase(useCaseImpl: CartUseCaseImpl): CartUseCase
}