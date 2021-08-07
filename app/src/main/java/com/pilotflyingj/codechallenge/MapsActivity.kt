package com.pilotflyingj.codechallenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.pilotflyingj.codechallenge.viewmodel.MapsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var viewModel: MapsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        (supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment)?.let {
            it.getMapAsync(this)
        }

        viewModel = ViewModelProvider(this).get(MapsViewModel::class.java)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val unitedStates = LatLng(38.0, -97.0)
        // center camera on the entire USA
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(unitedStates))
        // subscribe to live data for view model so that markers get added
        viewModel.siteResponse.observe(this) { sites ->
            println(sites)
            sites.forEach {
                googleMap.addMarker(MarkerOptions().position(it.location))
            }
        }
        //make sure rotation works
        if(viewModel.siteResponse.value.isNullOrEmpty()){
            viewModel.getSites()
        }
    }
}