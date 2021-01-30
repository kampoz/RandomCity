package com.kamil.randomcity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), Communicator {

    private var isDualPane = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // temporary without view model
        val repository = CitiesRepository(Producer())

        GlobalScope.launch(Dispatchers.Main) {
            val cities = repository.getCities()
            Toast.makeText(this@MainActivity, cities.toString(), Toast.LENGTH_LONG).show()
        }
        // end

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