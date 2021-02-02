package com.kamil.randomcity

import android.content.*
import android.os.Bundle

import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), Communicator {

    private var isDualPane = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentBView = findViewById<View>(R.id.fragment_detail)
        isDualPane = fragmentBView?.visibility == View.VISIBLE
    }

    // when we are in tablet or in phone
    override fun displayDetails(city: String, color: String) {
        if (isDualPane) {
            val fragmentDetail = supportFragmentManager.findFragmentById(R.id.fragment_detail) as FragmentDetail?
            fragmentDetail?.displayDetails(city, color)
        } else {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("city", city)
            intent.putExtra("color", color)
            startActivity(intent)
        }
    }


}