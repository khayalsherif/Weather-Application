package com.example.weatherappwithcompose

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.weatherappwithcompose.graphs.RootNavGraph
import com.example.weatherappwithcompose.ui.theme.app_theme.AppTheme.AppTheme
import com.example.weatherappwithcompose.ui.theme.colors.lightColors
import com.example.weatherappwithcompose.view_model.MainViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.AndroidEntryPoint

@Suppress("OPT_IN_IS_NOT_ENABLED")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContent {
            WeatherApplicationMain()
        }
    }

    @Composable
    fun WeatherApplicationMain() {

        val colors = lightColors()

        AppTheme(colors = colors) {
            Surface {
                MultiplePermission()
            }
        }
    }

    private fun getDeviceLocation(viewModel: MainViewModel) {
        val fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this)

        try {
            val locationResult = fusedLocationProviderClient.lastLocation

            locationResult.addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    val lastKnownLocation = task.result
                    if (lastKnownLocation != null) {
                        viewModel.currentUserLocation(
                            LatLng(
                                lastKnownLocation.altitude,
                                lastKnownLocation.longitude
                            )
                        )
                    }
                } else {
                    Log.d("Exception", " Current User location is null")
                }
            }
        } catch (e: SecurityException) {
            Log.d("Exception", "Exception:  ${e.message.toString()}")
        }
    }

    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    private fun MultiplePermission() {
        val locationPermissionsState = rememberMultiplePermissionsState(
            listOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
            )
        )
        if (locationPermissionsState.allPermissionsGranted) {
            getDeviceLocation(viewModel)
            RootNavGraph(navController = rememberNavController())
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val allPermissionsRevoked =
                    locationPermissionsState.permissions.size ==
                            locationPermissionsState.revokedPermissions.size

                val textToShow = if (!allPermissionsRevoked) {
                    // If not all the permissions are revoked, it's because the user accepted the COARSE
                    // location permission, but not the FINE one.
                    "Yay! Thanks for letting me access your approximate location. " +
                            "But you know what would be great? If you allow me to know where you " +
                            "exactly are. Thank you!"
                } else if (locationPermissionsState.shouldShowRationale) {
                    // Both location permissions have been denied
                    "Getting your exact location is important for this app. " +
                            "Please grant us fine location. Thank you :D"
                } else {
                    // First time the user sees this feature or the user doesn't want to be asked again
                    "This feature requires location permission"
                }

                val buttonText = if (!allPermissionsRevoked) {
                    "Allow precise location"
                } else {
                    "Request permissions"
                }

                Text(text = textToShow)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { locationPermissionsState.launchMultiplePermissionRequest() }) {
                    Text(buttonText)
                }
            }
        }
    }

    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    private fun SinglePermission() {
        val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
        if (cameraPermissionState.hasPermission) {
            Text("Camera permission Granted")
        } else {
            Column {
                val textToShow = if (cameraPermissionState.hasPermission) {
                    "The camera is important for this app. Please grant the permission."
                } else {
                    "Camera not available"
                }

                Text(textToShow)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
                    Text("Request permission")
                }
            }
        }
    }

}
