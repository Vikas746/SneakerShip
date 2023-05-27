package com.example.sneakership.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sneakership.features.sneakerDetails.dao.SneakerDetailDao
import com.example.sneakership.features.sneakerDetails.models.SneakerDetail

@Database(entities = [SneakerDetail::class], version = 1, exportSchema = false)
abstract class SneakerShipDataBase : RoomDatabase() {
    abstract fun sneakerDetailDao(): SneakerDetailDao
}