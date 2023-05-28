package com.example.sneakership.features.home.usecase

import com.example.sneakership.features.home.repository.SneakersRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class HomeUseCaseTest {

    @Mock
    lateinit var sneakersRepository: SneakersRepository

    private lateinit var homeUseCaseImpl: HomeUseCaseImpl

    @Before
    fun setUp() {
        homeUseCaseImpl = HomeUseCaseImpl(sneakersRepository)
    }

    @Test
    fun test_fetchSneakers() {
        homeUseCaseImpl.fetchSneakers()
        verify(sneakersRepository).fetchSneakers()
    }
}