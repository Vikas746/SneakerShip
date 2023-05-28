package com.example.sneakership.features.sneakerDetails.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.sneakership.MainDispatcherRule
import com.example.sneakership.features.sneakerDetails.usecase.SneakerDetailUseCase
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
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
class SneakerDetailViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: SneakerDetailViewModel

    @Mock
    lateinit var useCase: SneakerDetailUseCase

    @Before
    fun setUp() {
        viewModel = SneakerDetailViewModel(SavedStateHandle(), useCase)
    }

    @Test
    fun test_canAddToCart_success() {
        viewModel.sneakerDetailUiState.selectedSize.value = 7
        viewModel.sneakerDetailUiState.selectedColor.value = "FF0000"
        assertTrue(viewModel.canAddToCart())
    }

    @Test
    fun test_canAddToCart_when_size_not_selected() {
        viewModel.sneakerDetailUiState.selectedColor.value = "FF0000"
        assertFalse(viewModel.canAddToCart())
    }

    @Test
    fun test_canAddToCart_when_color_not_selected() {
        viewModel.sneakerDetailUiState.selectedSize.value = 7
        assertFalse(viewModel.canAddToCart())
    }

    @Test
    fun test_canAddToCart_when_size_and_color_not_selected() {
        viewModel.sneakerDetailUiState.selectedSize.value = 7
        assertFalse(viewModel.canAddToCart())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_addToCart() {
        runTest {
            viewModel.addToCart()
            verify(useCase).addToCart(any(), any(), any())
        }
    }
}