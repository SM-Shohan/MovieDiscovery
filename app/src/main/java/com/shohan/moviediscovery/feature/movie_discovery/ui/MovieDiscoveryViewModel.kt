package com.shohan.moviediscovery.feature.movie_discovery.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shohan.moviediscovery.feature.movie_discovery.domain.model.MovieResponse
import com.shohan.moviediscovery.feature.movie_discovery.domain.usecase.TrendingMovieUseCase
import com.shohan.moviediscovery.uiUtility.utilities.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDiscoveryViewModel @Inject constructor(
    private val trendingMovieUseCase: TrendingMovieUseCase
) : ViewModel() {
    private val _demoString = MutableStateFlow<String> ("")
    var demoString: StateFlow<String> = _demoString.asStateFlow()

    private val _trendingMovieState = MutableStateFlow<UiState<MovieResponse>>(UiState.Idle)
    val trendingMovieState: StateFlow<UiState<MovieResponse>> = _trendingMovieState.asStateFlow()

    init {
        _demoString.value = "Hello World"
        viewModelScope.launch {
            trendingMovieUseCase().collect { state ->
                _trendingMovieState.value = state
            }
        }
    }



}