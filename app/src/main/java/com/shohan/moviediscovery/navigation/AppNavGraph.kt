package com.shohan.moviediscovery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.shohan.moviediscovery.feature.movie_details.ui.MovieDetailsScreenRoute
import com.shohan.moviediscovery.feature.movie_discovery.ui.MovieDiscoveryScreenRoute

@Composable
fun AppNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Destinations.Discovery.route
    ){
        composable(Destinations.Discovery.route){
            MovieDiscoveryScreenRoute(
                onClickMovie = { id->
                    navController.navigate(Destinations.MovieDetails.createRoute(id))
                }
            )
        }

        composable(
            route = Destinations.MovieDetails.route,
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
            MovieDetailsScreenRoute(movieId = movieId)
        }

    }
}