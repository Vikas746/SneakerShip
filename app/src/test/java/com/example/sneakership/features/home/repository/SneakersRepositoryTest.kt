package com.example.sneakership.features.home.repository

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class SneakersRepositoryTest {

    private lateinit var sneakersRepository: SneakersRepositoryImpl

    @Before
    fun setUp() {
        sneakersRepository = SneakersRepositoryImpl()
    }

    @Test
    fun test_fetchSneakers() {
        val sneakers = sneakersRepository.fetchSneakers()
        assertEquals(100, sneakers.size)
    }
}