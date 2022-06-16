package com.example.weatherappwithcompose.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherappwithcompose.screens.MainScreen

@Composable
fun RootNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Graph.MAIN,
        route = Graph.ROOT
    ) {
        composable(route = Graph.MAIN) {
            MainScreen()
        }
    }
}

object Graph {
    const val MAIN = "main_graph"
    const val ROOT = "root_graph"
}