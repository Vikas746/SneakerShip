package com.example.sneakership.features.cart.usecase

import com.example.sneakership.MainDispatcherRule
import com.example.sneakership.features.sneakerDetails.models.SneakerDetail
import com.example.sneakership.features.sneakerDetails.repository.SneakerDetailRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class CartUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    lateinit var repository: SneakerDetailRepository

    private lateinit var useCase: CartUseCaseImpl

    @Before
    fun setUp() {
        useCase = CartUseCaseImpl(repository)
    }

    @Test
    fun test_fetchCartItems() {
        useCase.fetchCartItems()
        verify(repository).fetchSneakers()
    }

    @Test
    fun test_deleteCartItem() {
        runTest {
            val sneaker = SneakerDetail("", "", 7, "", 100)
            useCase.deleteCartItem(sneaker)
            verify(repository).deleteSneakerDetail(sneaker)
        }
    }
}