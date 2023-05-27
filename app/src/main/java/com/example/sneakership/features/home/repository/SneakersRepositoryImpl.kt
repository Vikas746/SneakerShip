package com.example.sneakership.features.home.repository

import androidx.compose.ui.graphics.Color
import com.example.sneakership.features.home.models.Sneaker
import java.util.Random
import javax.inject.Inject

class SneakersRepositoryImpl @Inject constructor() : SneakersRepository {
    override fun fetchSneakers(): List<Sneaker> {
        return getDummyData()
    }

    private fun getDummyData(): List<Sneaker> {
        val sneakers = mutableListOf<Sneaker>()

        val brands = listOf("Adidas", "Nike", "Puma", "Reebok", "Sparks")
        val random = Random()

        brands.forEach { brand ->
            for (i in 1..10) {
                sneakers.add(
                    Sneaker(
                        name = brand + i,
                        brand = brand,
                        price = random.nextInt(8000) + 2000,
                        sizes = listOf(7, 8, 9),
                        colors = listOf(Color.White, Color.Black, Color.Yellow)
                    )
                )
            }
        }
        return sneakers
    }
}