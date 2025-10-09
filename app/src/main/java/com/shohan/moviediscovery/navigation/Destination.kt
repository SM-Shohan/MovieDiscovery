package com.shohan.moviediscovery.navigation

sealed class Destinations(val route: String) {
    object Discovery : Destinations("discovery")

    object MovieDetails : Destinations("movie_details/{movieId}") {
        fun createRoute(movieId: Int) = "movie_details/$movieId"
    }
}

