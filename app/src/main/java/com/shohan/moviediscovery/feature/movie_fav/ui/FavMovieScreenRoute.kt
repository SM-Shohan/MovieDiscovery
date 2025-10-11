package com.shohan.moviediscovery.feature.movie_fav.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FavMovieScreenRoute(
    viewModel: FavMovieViewModel = hiltViewModel(),
    onBackClick: () -> Unit
)
{
    val favMovies by viewModel.favMovies.collectAsState()

    FavMovieScreen(
        favMovieList = favMovies,
        onBackClick = {
            onBackClick()
        }
    )

}