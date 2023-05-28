package com.example.sneakership.features.sneakerDetails.usecase

import com.example.sneakership.MainDispatcherRule
import com.example.sneakership.features.home.models.Sneaker
import com.example.sneakership.features.sneakerDetails.repository.SneakerDetailRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class SneakerDetailUseCaseTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var useCase: SneakerDetailsUseCaseImpl

    @Mock
    lateinit var repository: SneakerDetailRepository

    @Before
    fun setUp() {
        useCase = SneakerDetailsUseCaseImpl(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_addToCart() {
        runTest {
            useCase.addToCart(Sneaker(), 7, "FF0000")
            verify(repository).insertSneakerDetail(any())
        }
    }
}