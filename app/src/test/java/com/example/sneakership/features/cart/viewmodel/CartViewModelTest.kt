package com.example.sneakership.features.cart.viewmodel

import com.example.sneakership.MainDispatcherRule
import com.example.sneakership.features.cart.usecase.CartUseCase
import com.example.sneakership.features.sneakerDetails.models.SneakerDetail
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class CartViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    lateinit var useCase: CartUseCase

    private lateinit var viewModel: CartViewModel

    @Before
    fun setUp() {
        val sneaker1 = SneakerDetail("", "", 7, "", 100)
        val sneaker2 = SneakerDetail("", "", 7, "", 200)
        whenever(useCase.fetchCartItems()).thenReturn(flowOf(listOf(sneaker1, sneaker2)))
        viewModel = CartViewModel(useCase)
    }

    @Test
    fun test_fetchCartItems() {
        runTest {
            assertEquals(2, viewModel.cartUiState.cartItems.size)
            assertEquals(300, viewModel.cartUiState.subTotal)
            assertEquals((0.05 * 300).toInt(), viewModel.cartUiState.tax)
            assertEquals((0.05 * 300).toInt() + 300, viewModel.cartUiState.total)
        }
    }

    @Test
    fun test_deleteCartItem() {
        runTest {
            val sneaker = SneakerDetail("", "", 7, "", 100)
            viewModel.deleteCartItem(sneaker)
            verify(useCase).deleteCartItem(sneaker)
        }
    }
}