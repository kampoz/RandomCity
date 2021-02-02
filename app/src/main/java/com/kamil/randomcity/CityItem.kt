package com.kamil.randomcity

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.util.*

data class CityItem(

    val cityName: String,
    val date: String,
    val color: String
)


object DataProducer {
    private val cities = listOf("Gdańsk", "Warszawa", "Poznań", "Białystok", "Wrocław", "Katowice", "Kraków")
    private val colors = listOf("Yellow", "Green", "Blue", "Red", "Black")

    val data: CityItem
        @RequiresApi(Build.VERSION_CODES.O)
        get() {
            val city = cities.random()
            val color = colors.random()
            val date = LocalDateTime.now()

            return CityItem(city, date.toString(), color)
        }
}