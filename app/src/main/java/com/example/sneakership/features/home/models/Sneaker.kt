package com.example.sneakership.features.home.models

data class Sneaker(
    val name: String = "",
    val brand: String = "",
    val price: Int = 0,
    val sizes: List<Int> = listOf(),
    val colors: List<String> = listOf()
)
