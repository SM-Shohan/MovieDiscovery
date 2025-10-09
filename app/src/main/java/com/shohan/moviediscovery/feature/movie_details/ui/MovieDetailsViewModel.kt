package com.shohan.moviediscovery.feature.movie_details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shohan.moviediscovery.feature.movie_details.domain.model.MovieDetailsResponse
import com.shohan.moviediscovery.feature.movie_details.domain.usecase.MovieDetailsUseCase
import com.shohan.moviediscovery.uiUtility.utilities.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel@Inject constructor(
    private val movieDetailsUseCase: MovieDetailsUseCase
): ViewModel() {

    private val _movieDetailsState = MutableStateFlow<UiState<MovieDetailsResponse>>(UiState.Idle)
    val movieDetailsState: StateFlow<UiState<MovieDetailsResponse>> = _movieDetailsState.asStateFlow()

    fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            movieDetailsUseCase(movieId = movieId).collect { state ->
                _movieDetailsState.value = state
            }
        }
    }
}