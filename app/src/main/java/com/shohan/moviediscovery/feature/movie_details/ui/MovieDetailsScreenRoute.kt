package com.shohan.moviediscovery.feature.movie_details.ui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.shohan.moviediscovery.feature.movie_details.domain.model.MovieDetailsResponse
import com.shohan.moviediscovery.uiUtility.utilities.UiState

@Composable
fun MovieDetailsScreenRoute(
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    movieId: Int

){

    val context = LocalContext.current

    val movieDetailsState by viewModel.movieDetailsState.collectAsState()
    var isLoading by remember { mutableStateOf(false) }
    var movieDetailsResponse by remember { mutableStateOf<MovieDetailsResponse?>(null) }

    LaunchedEffect(Unit) {
        viewModel.loadMovieDetails(movieId)
    }

    LaunchedEffect(movieDetailsState) {
        when (movieDetailsState) {
            is UiState.Loading -> {
                isLoading = true
            }

            is UiState.Success -> {
                isLoading = false
                movieDetailsResponse = (movieDetailsState as UiState.Success<MovieDetailsResponse>).data
            }

            is UiState.Error -> {
                isLoading = false
                Toast.makeText(
                    context,
                    (movieDetailsState as UiState.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> {
                //idle
            }
        }
    }

    MovieDetailsScreen(
        movieResponse = movieDetailsResponse,
        isLoading = isLoading
    )

}