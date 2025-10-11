package com.shohan.moviediscovery.feature.movie_details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shohan.moviediscovery.feature.movie_details.domain.model.MovieDetailsResponse
import com.shohan.moviediscovery.feature.movie_details.domain.usecase.DeleteFavMovieUseCase
import com.shohan.moviediscovery.feature.movie_details.domain.usecase.IsFavMovieUseCase
import com.shohan.moviediscovery.feature.movie_details.domain.usecase.MovieDetailsUseCase
import com.shohan.moviediscovery.feature.movie_details.domain.usecase.SaveFavMovieUseCase
import com.shohan.moviediscovery.uiUtility.utilities.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel@Inject constructor(
    private val movieDetailsUseCase: MovieDetailsUseCase,
    private val saveFavMovieUseCase: SaveFavMovieUseCase,
    private val isFavMovieUseCase: IsFavMovieUseCase,
    private val deleteFavMovieUseCase: DeleteFavMovieUseCase
): ViewModel() {

    private val _movieDetailsState = MutableStateFlow<UiState<MovieDetailsResponse>>(UiState.Idle)
    val movieDetailsState: StateFlow<UiState<MovieDetailsResponse>> = _movieDetailsState.asStateFlow()

    private val _isSavedToFav = MutableStateFlow(false)
    val isSavedToFav = _isSavedToFav.asStateFlow()

    fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            movieDetailsUseCase(movieId = movieId).collect { state ->
                _movieDetailsState.value = state
            }
        }
    }

    fun checkIfSavedToFav(id: Int) {
        viewModelScope.launch {
            _isSavedToFav.value = isFavMovieUseCase(id)
        }
    }

    fun toggleSaveFav(movie: MovieDetailsResponse) {
        viewModelScope.launch {
            if (isFavMovieUseCase(movie.id)) {
                deleteFavMovieUseCase(movie)
                _isSavedToFav.value = false
            } else {
                saveFavMovieUseCase(movie)
                _isSavedToFav.value = true
            }
        }
    }

}