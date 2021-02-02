package com.kamil.randomcity

import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class FragmentMaster : Fragment(), ServiceConnection {

    private lateinit var viewModel: CitiesViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var myReceiver: BroadcastReceiver
    private val TAG = "FragmentMaster "

    private var isBound: Boolean = false
    private lateinit var mService: MyService

    val mHandleMessageReceiver = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val message = intent?.getExtras()?.getString("message")
            println("FragmentMaster message from broadcast: $message")
        }
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        context.registerReceiver(mHandleMessageReceiver, IntentFilter("com.kamil.MESSAGE"))
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        context?.unregisterReceiver(mHandleMessageReceiver)
//    }

//    private val connection = object : ServiceConnection {
//        override fun onServiceConnected(className: ComponentName, iBinder: IBinder) {
//            val binder = iBinder as MyService.LocalBinder
//            mService = binder.getService()
//            mService.viewModel = viewModel
//            isBound = true
//        }
//        override fun onServiceDisconnected(arg0: ComponentName) {
//            isBound = false
//        }
//    }

    override fun onServiceConnected(className: ComponentName, iBinder: IBinder) {
        val binder = iBinder as MyService.LocalBinder
        mService = binder.getService()
//        mService.viewModel = viewModel
        mService.setViewModel2(viewModel)

        isBound = true
    }
    override fun onServiceDisconnected(arg0: ComponentName) {
        isBound = false
    }

    override fun onResume() {
        super.onResume()
        val intent = Intent(context, MyService::class.java)
        context?.bindService(intent, this, Context.BIND_AUTO_CREATE)
    }

    override fun onPause() {
        super.onPause()
        if (isBound) {
            context?.unbindService(this)
            isBound = false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.master_fragment, container, false)
        recyclerView = view.findViewById(R.id.recyclerview_cities)
        viewModel = CitiesViewModelFactory().create(CitiesViewModel::class.java)

        println("$TAG viewModel object: $viewModel")
        val myAdapter = viewModel.cities.value?.let { CitiesAdapter(it) }

//        val citiesObserver2 = object: Observer<MutableList<CityItem>> {
//            override fun onChanged(t: MutableList<CityItem>?) {
//                recyclerView.adapter?.notifyDataSetChanged()
//                println("${TAG }Observer detect change")
//            }
//        }

        val wordObserver = object: Observer<String> {
            override fun onChanged(t: String) {
                recyclerView.adapter?.notifyDataSetChanged()
                println("$TAG wordObserver detect change")
            }
        }
        viewModel.cities.observe(viewLifecycleOwner, citiesObserver2)
//        viewModel.testWord.observe(viewLifecycleOwner, wordObserver)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = myAdapter

        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)





        myReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                println("MyIntentService BroadcastReceiver received action")
            }
        }
        context?.registerReceiver(myReceiver, IntentFilter("myAction"))

    }

    private fun generateCities(): List<CityItem> {
        val list =  ArrayList<CityItem>()
        for (i in 1..3) {
            val cityItem = DataProducer.data
            list.add(cityItem)
        }
        return list
    }


}