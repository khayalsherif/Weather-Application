package com.example.weatherappwithcompose

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.weatherappwithcompose.graphs.RootNavGraph
import com.example.weatherappwithcompose.ui.theme.WeatherAppWithComposeTheme
import com.example.weatherappwithcompose.view_model.MainViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private lateinit var viewModel: MainViewModel
private lateinit var fusedLocationClient: FusedLocationProviderClient

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        setContent {
            WeatherAppWithComposeTheme {
                RootNavGraph(navController = rememberNavController())
            }
        }

        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            findLocation()
            collectTheResponse()
        } else {
            fetchLocation()
        }
    }

    private fun collectTheResponse() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                if (!state.isLoading) {
                    if (state.hasInternet) {
                        if (state.error.isNotBlank()) {
                            println(state.error)
                        } else {
                            println(state.weather!!.daily[0].temp)
                        }
                    } else {
                        println(state.error)
                    }
                }
            }
        }
    }

    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) !=
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            requestMultiplePermissions.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
            return
        }
    }

    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
                if (it.key == Manifest.permission.ACCESS_FINE_LOCATION && it.value
                ) {
                    findLocation()
                    collectTheResponse()
                }
            }
        }

    private fun findLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                location?.let {
                    println(location)
                }
            }
    }
}
