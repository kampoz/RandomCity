package com.kamil.randomcity

class Producer {

    private val cities = listOf("Gdańsk", "Warszawa", "Poznań", "Białystok", "Wrocław", "Katowice", "Kraków")
    private val colors = listOf("Yellow", "Green", "Blue", "Red", "Black")

    suspend fun produceCities() : MutableList<CityItem> {
        val dataSet = mutableListOf<CityItem>()
        for (city in cities) {
            val color = colors.random()
            val cityItem = CityItem(city, color)
            dataSet.add(cityItem)
            println(cityItem)
        }

        return dataSet
    }
}
