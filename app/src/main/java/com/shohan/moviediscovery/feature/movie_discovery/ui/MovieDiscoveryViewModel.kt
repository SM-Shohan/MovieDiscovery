package com.shohan.moviediscovery.feature.movie_discovery.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shohan.moviediscovery.feature.movie_discovery.domain.model.PopularMovieResponse
import com.shohan.moviediscovery.feature.movie_discovery.domain.model.TrendingMovieResponse
import com.shohan.moviediscovery.feature.movie_discovery.domain.usecase.PopularMovieUseCase
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
    private val trendingMovieUseCase: TrendingMovieUseCase,
    private val popularMovieUseCase: PopularMovieUseCase
) : ViewModel() {

    private val _trendingMovieState = MutableStateFlow<UiState<TrendingMovieResponse>>(UiState.Idle)
    val trendingMovieState: StateFlow<UiState<TrendingMovieResponse>> = _trendingMovieState.asStateFlow()
    private val _popularMovieState = MutableStateFlow<UiState<PopularMovieResponse>>(UiState.Idle)
    val popularMovieState: StateFlow<UiState<PopularMovieResponse>> = _popularMovieState.asStateFlow()

    init {
        viewModelScope.launch {
            trendingMovieUseCase().collect { state ->
                _trendingMovieState.value = state
            }
            popularMovieUseCase().collect { state ->
                _popularMovieState.value = state
            }
        }
    }



}