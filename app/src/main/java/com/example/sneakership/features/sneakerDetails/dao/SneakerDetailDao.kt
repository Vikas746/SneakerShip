package com.example.sneakership.features.sneakerDetails.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.sneakership.features.sneakerDetails.models.SneakerDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface SneakerDetailDao {

    @Insert
    suspend fun insertSneaker(sneaker: SneakerDetail)

    @Delete
    suspend fun deleteSneaker(sneaker: SneakerDetail)

    @Query("SELECT * FROM SneakerDetail")
    fun fetchSneakers(): Flow<List<SneakerDetail>>
}