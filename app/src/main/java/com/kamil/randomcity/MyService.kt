package com.kamil.randomcity

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder

class MyService : Service() {

    private val binder = LocalBinder()
    private val TAG = "MyService "
    private var isPaused = false
    private lateinit var handler: Handler
    val cities = ArrayList<CityItem>()
    val context = this


    lateinit var viewModel: CitiesViewModel
    private lateinit var factory: CitiesViewModelFactory

    inner class LocalBinder : Binder() {
        fun getService(): MyService = this@MyService
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        handler = Handler()
//        isPaused = true
        startTask()

    }

    fun setViewModel2(citiesViewModel: CitiesViewModel) {
        viewModel = citiesViewModel
    }

    fun startTask() {
        val runnable: Runnable = object : Runnable {
            override fun run() {
                if (isPaused) {
                    println("$TAG run: removing callbacks")
                    handler.removeCallbacks(this) // remove callbacks from runnable
                    pauseTask() // ?
                } else {
                    Thread {
                        for (i in 1..15) {
                            val cityItem = DataProducer.data
                            viewModel.addCity(cityItem)
//                            viewModel.cities.value?.add(cityItem)
//                            cities.add(cityItem)
//                            sendMessage(context, "my message")
//                            println("$TAG task is running ${cityItem.toString()}")
//                            println("$TAG viewmodel _cities size: ${viewModel.cities.value?.size}")
//                            viewModel.addCity(cityItem)

//                            viewModel.testWord.postValue("changed word $i")
//                            println("$TAG viewmodel testWord: ${viewModel.testWord.value}")
//                            println("$TAG viewModel object: $viewModel")

                            Thread.sleep(2000)
                        }
                    }.start()
                }
            }
        }
        handler.postDelayed(runnable, 100)
    }

    fun pauseTask() {
        isPaused = true
    }

    fun unPauseTask() {
        isPaused = false
        startTask()
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        println("$TAG onTaskRemoved: called.");
        stopSelf()
    }

    private fun sendMessage(context: Context, message: String) {
        val intent = Intent("com.kamil.MESSAGE")
        intent.putExtra("message", message)
        context.sendBroadcast(intent)
    }
}