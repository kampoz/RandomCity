package com.kamil.randomcity

class CitiesRepository(
    private val producer: Producer
) {

//    suspend fun getCities() = producer.produceCities()
    suspend fun getCities() = DataProducer.data
}