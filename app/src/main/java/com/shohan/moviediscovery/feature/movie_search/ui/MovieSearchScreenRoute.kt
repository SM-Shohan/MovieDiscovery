package com.shohan.moviediscovery.feature.movie_search.ui

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
import com.shohan.moviediscovery.feature.movie_search.domain.model.SearchMovieResponse
import com.shohan.moviediscovery.uiUtility.utilities.UiState

@Composable
fun MovieSearchScreenRoute(
    viewModel: SearchMovieViewModel = hiltViewModel(),
    onClickMovie: (movieId: Int) -> Unit,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val searchMovieState by viewModel.searchMovieState.collectAsState()
    var searchMovieResponse by remember { mutableStateOf<SearchMovieResponse?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(searchMovieState) {
        when (searchMovieState) {
            is UiState.Loading -> {
                isLoading = true
            }

            is UiState.Success -> {
                isLoading = false
                searchMovieResponse = (searchMovieState as UiState.Success<SearchMovieResponse>).data
            }

            is UiState.Error -> {
                isLoading = false
                Toast.makeText(context, (searchMovieState as UiState.Error).message, Toast.LENGTH_SHORT).show()
            }

            else -> {
                //idle
            }
        }
    }

    MovieSearchScreen(
        isLoading = isLoading,
        searchMovieResponse = searchMovieResponse,
        onChangeSearchText = { query ->
            viewModel.searchMovies(query)
        },
        onClickMovie = {id->
            onClickMovie(id)
        },
        onBackClick = {
            onBackClick()
        }
    )
}