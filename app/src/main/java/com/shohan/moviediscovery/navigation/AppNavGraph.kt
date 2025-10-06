package com.shohan.moviediscovery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shohan.moviediscovery.feature.movie_discovery.ui.MovieDiscoveryScreenRoute

@Composable
fun AppNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = "home"
    ){
        composable("home"){
            MovieDiscoveryScreenRoute()
        }

    }
}