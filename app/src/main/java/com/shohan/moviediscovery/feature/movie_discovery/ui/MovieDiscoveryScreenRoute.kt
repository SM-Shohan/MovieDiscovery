package com.shohan.moviediscovery.feature.movie_discovery.ui

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
import com.shohan.moviediscovery.feature.movie_discovery.domain.model.MovieResponse
import com.shohan.moviediscovery.uiUtility.utilities.UiState

@Composable
fun MovieDiscoveryScreenRoute(
    viewModel: MovieDiscoveryViewModel = hiltViewModel(),
    onClickMovie: (movieId: Int) -> Unit
)
{
    val context = LocalContext.current
    val trendingMovieState by viewModel.trendingMovieState.collectAsState()

    var movieResponse by remember { mutableStateOf<MovieResponse?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(trendingMovieState) {
        when (trendingMovieState) {
            is UiState.Loading -> {
                isLoading = true
            }

            is UiState.Success -> {
                isLoading = false
                movieResponse = (trendingMovieState as UiState.Success<MovieResponse>).data
            }

            is UiState.Error -> {
                isLoading = false
                Toast.makeText(context, (trendingMovieState as UiState.Error).message, Toast.LENGTH_SHORT).show()
            }

            else -> {
                //idle
            }
        }
    }
    MovieDiscoveryScreen(
        isLoading = isLoading,
        movieResponse = movieResponse,
        onClickMovie = { id->
            onClickMovie(id)
        }
    )

}
