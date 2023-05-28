package com.example.sneakership.features.sneakerDetails.repository

import com.example.sneakership.features.sneakerDetails.models.SneakerDetail
import kotlinx.coroutines.flow.Flow

interface SneakerDetailRepository {

    /**
     * This method adds the sneaker to database.
     */
    suspend fun insertSneakerDetail(sneakerDetail: SneakerDetail)

    /**
     * This method deletes the sneaker from database.
     */
    suspend fun deleteSneakerDetail(sneakerDetail: SneakerDetail)

    /**
     * This method provides all the sneakers in database.
     */
    fun fetchSneakers(): Flow<List<SneakerDetail>>
}