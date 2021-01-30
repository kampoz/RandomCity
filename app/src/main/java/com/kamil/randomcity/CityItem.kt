package com.kamil.randomcity

data class CityItem(

    val name: String,

    val color: String
)


object DataProducer {
    private val cities = listOf("Gdańsk", "Warszawa", "Poznań", "Białystok", "Wrocław", "Katowice", "Kraków")
    private val colors = listOf("Yellow", "Green", "Blue", "Red", "Black")

    val data: List<CityItem>
        get() {
            val dataList = ArrayList<CityItem>()

            for (city in cities) {
                val color = colors.random()
                val cityItem = CityItem(city, color)
                dataList.add(cityItem)
                println(cityItem)
            }

            return dataList
        }
}