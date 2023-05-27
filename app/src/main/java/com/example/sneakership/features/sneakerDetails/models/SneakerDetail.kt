package com.example.sneakership.features.sneakerDetails.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SneakerDetail(
    val name: String,
    val brand: String,
    val size: Int,
    val color: String,
    val price: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
