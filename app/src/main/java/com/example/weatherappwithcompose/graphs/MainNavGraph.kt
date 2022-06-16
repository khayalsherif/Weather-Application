package com.example.weatherappwithcompose.graphs

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherappwithcompose.BottomBarScreen
import com.example.weatherappwithcompose.screens.contents.HomeContent
import com.example.weatherappwithcompose.screens.contents.ProfileContent
import com.example.weatherappwithcompose.view_model.MainViewModel

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            val viewModel = hiltViewModel<MainViewModel>()
            HomeContent(viewModel)
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileContent()
        }
    }
}