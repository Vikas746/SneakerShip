package com.example.sneakership.features.home.viewmodels

import com.example.sneakership.features.home.models.HomeToolbarState
import com.example.sneakership.features.home.models.Sneaker
import com.example.sneakership.features.home.models.SortOptions
import com.example.sneakership.features.home.usecase.HomeUseCase
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @Mock
    lateinit var homeUseCase: HomeUseCase

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        whenever(homeUseCase.fetchSneakers()).thenReturn(
            listOf(
                Sneaker("dummy sneaker", price = 100),
                Sneaker("test sneaker", price = 200)
            )
        )
        homeViewModel = HomeViewModel(homeUseCase)
    }

    @Test
    fun test_search_success() {
        homeViewModel.homeUiState.searchText.value = "dummy"
        homeViewModel.search()
        assertEquals(1, homeViewModel.homeUiState.sneakers.size)
    }

    @Test
    fun test_search_failure() {
        homeViewModel.homeUiState.searchText.value = "puma"
        homeViewModel.search()
        assertEquals(0, homeViewModel.homeUiState.sneakers.size)
    }

    @Test
    fun test_onSearchCleared() {
        homeViewModel.onSearchCleared()
        assertEquals(HomeToolbarState.TITLE, homeViewModel.homeUiState.toolbarState.value)
        assertEquals("", homeViewModel.homeUiState.searchText.value)
    }

    @Test
    fun test_sort_AtoZ() {
        homeViewModel.homeUiState.sortOption.value = SortOptions.A_TO_Z
        homeViewModel.sort()
        assertEquals("dummy sneaker", homeViewModel.homeUiState.sneakers[0].name)
    }

    @Test
    fun test_sort_ZtoA() {
        homeViewModel.homeUiState.sortOption.value = SortOptions.Z_TO_A
        homeViewModel.sort()
        assertEquals("test sneaker", homeViewModel.homeUiState.sneakers[0].name)
    }

    @Test
    fun test_sort_LowToHigh() {
        homeViewModel.homeUiState.sortOption.value = SortOptions.PRICE_LOW_TO_HIGH
        homeViewModel.sort()
        assertEquals(100, homeViewModel.homeUiState.sneakers[0].price)
    }

    @Test
    fun test_sort_HighToLow() {
        homeViewModel.homeUiState.sortOption.value = SortOptions.PRICE_HIGH_TO_LOW
        homeViewModel.sort()
        assertEquals(200, homeViewModel.homeUiState.sneakers[0].price)
    }

    @Test
    fun test_fetchSneakers() {
        verify(homeUseCase).fetchSneakers()
        assertEquals(2, homeViewModel.homeUiState.sneakers.size)
    }
}