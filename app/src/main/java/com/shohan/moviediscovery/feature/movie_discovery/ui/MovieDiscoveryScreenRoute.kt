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
import com.shohan.moviediscovery.feature.movie_discovery.domain.model.PopularMovieResponse
import com.shohan.moviediscovery.feature.movie_discovery.domain.model.TrendingMovieResponse
import com.shohan.moviediscovery.uiUtility.utilities.UiState

@Composable
fun MovieDiscoveryScreenRoute(
    viewModel: MovieDiscoveryViewModel = hiltViewModel(),
    onClickMovie: (movieId: Int) -> Unit,
    onToolbarSearchClick: () -> Unit,
    onToolbarFavoriteClick: () -> Unit
)
{
    val context = LocalContext.current
    val trendingMovieState by viewModel.trendingMovieState.collectAsState()
    val popularMovieState by viewModel.popularMovieState.collectAsState()

    var trendingMovieResponse by remember { mutableStateOf<TrendingMovieResponse?>(null) }
    var popularMovieResponse by remember { mutableStateOf<PopularMovieResponse?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(trendingMovieState) {
        when (trendingMovieState) {
            is UiState.Loading -> {
                isLoading = true
            }

            is UiState.Success -> {
                isLoading = false
                trendingMovieResponse = (trendingMovieState as UiState.Success<TrendingMovieResponse>).data
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


    LaunchedEffect(popularMovieState) {
        when (popularMovieState) {
            is UiState.Loading -> {
                isLoading = true
            }

            is UiState.Success -> {
                isLoading = false
                popularMovieResponse = (popularMovieState as UiState.Success<PopularMovieResponse>).data
            }

            is UiState.Error -> {
                isLoading = false
                Toast.makeText(context, (popularMovieState as UiState.Error).message, Toast.LENGTH_SHORT).show()
            }

            else -> {
                //idle
            }
        }
    }

    MovieDiscoveryScreen(
        isLoading = isLoading,
        trendingMovieResponse = trendingMovieResponse,
        popularMovieResponse = popularMovieResponse,
        onClickMovie = { id->
            onClickMovie(id)
        },
        onToolbarSearchClick = {
            onToolbarSearchClick()
        },
        onToolbarFavoriteClick = {
            onToolbarFavoriteClick()
        }
    )

}
