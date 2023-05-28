package com.example.sneakership.features.sneakerDetails.repository

import com.example.sneakership.MainDispatcherRule
import com.example.sneakership.features.sneakerDetails.dao.SneakerDetailDao
import com.example.sneakership.features.sneakerDetails.models.SneakerDetail
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
class SneakerDetailRepositoryTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    lateinit var sneakerDetailDao: SneakerDetailDao

    private lateinit var repository: SneakerDetailRepositoryImpl

    @Before
    fun setUp() {
        repository = SneakerDetailRepositoryImpl(sneakerDetailDao)
    }

    @Test
    fun test_insertSneakerDetail() {
        runTest {
            val sneakerDetails = SneakerDetail("", "", 7, "", 100)
            repository.insertSneakerDetail(sneakerDetails)
            verify(sneakerDetailDao).insertSneaker(sneakerDetails)
        }
    }

    @Test
    fun test_deleteSneakerDetail() {
        runTest {
            val sneakerDetails = SneakerDetail("", "", 7, "", 100)
            repository.deleteSneakerDetail(sneakerDetails)
            verify(sneakerDetailDao).deleteSneaker(sneakerDetails)
        }
    }

    @Test
    fun test_fetchSneakers() {
        repository.fetchSneakers()
        verify(sneakerDetailDao).fetchSneakers()
    }
}