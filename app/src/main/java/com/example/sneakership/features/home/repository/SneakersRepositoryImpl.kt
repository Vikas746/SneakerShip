package com.example.sneakership.features.home.repository

import com.example.sneakership.features.home.models.Sneaker
import java.util.Random
import javax.inject.Inject

class SneakersRepositoryImpl @Inject constructor() : SneakersRepository {

    /**
     * This method fetches sneakers data.
     */
    override fun fetchSneakers(): List<Sneaker> {
        return getDummyData()
    }

    /**
     * This method creates a dummy data based on hardcoded brands.
     * It provides 10 sneakers from each brand.
     */
    private fun getDummyData(): List<Sneaker> {
        val sneakers = mutableListOf<Sneaker>()

        val brands = listOf(
            "Adidas",
            "Nike",
            "Puma",
            "Reebok",
            "Sparks",
            "Bata",
            "Paragon",
            "Fila",
            "Liberty",
            "Lancer"
        )
        val random = Random()

        brands.forEach { brand ->
            for (i in 1..10) {
                sneakers.add(
                    Sneaker(
                        name = brand + i,
                        brand = brand,
                        price = random.nextInt(8000) + 2000,
                        sizes = listOf(7, 8, 9),
                        colors = listOf("FF0000", "00FF00", "0000FF")
                    )
                )
            }
        }
        return sneakers
    }
}