package com.example.sneakership.features.sneakerDetails.repository

import com.example.sneakership.features.sneakerDetails.models.SneakerDetail
import kotlinx.coroutines.flow.Flow

interface SneakerDetailRepository {
    suspend fun insertSneakerDetail(sneakerDetail: SneakerDetail)
    suspend fun deleteSneakerDetail(sneakerDetail: SneakerDetail)
    fun fetchSneakers(): Flow<List<SneakerDetail>>
}