package com.kamil.randomcity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CitiesViewModel(

    private val TAG: String = "CitiesViewModel"

) : ViewModel() {

    val cities = MutableLiveData<MutableList<CityItem>>()

    val testWord = MutableLiveData<String>()

    private val _isUpdating = MutableLiveData<Boolean>()
    private val _mBinder = MutableLiveData<MyService.LocalBinder>()

    init {
        cities.value = ArrayList()
        testWord.value = "init word"
    }

    fun addCity(cityItem: CityItem){
        val list = cities.value
        list?.add(cityItem)
        list?.sortedBy { it.cityName }
//        cities.value?.clear()
        cities.postValue(list)
    }
}