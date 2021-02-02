package com.kamil.randomcity

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService


//todo remove this class after addin bound service
class MyIntentService: JobIntentService() {

    override fun onHandleWork(intent: Intent) {
        println("MyIntentService onHandleWork")
//        while (true) {
//            println("MyIntentService is doing something")
//
//            val i = Intent("myAction")
//            sendBroadcast(i)
//            Thread.sleep(2000)
//        }
        for (i in 1..10) {
            println("MyIntentService is doing something")

            val i = Intent("myAction")
            sendBroadcast(i)
            Thread.sleep(2000)
        }
    }

    companion object {
        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, MyIntentService::class.java, 1, intent)
        }
    }
}