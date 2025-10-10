package com.shohan.moviediscovery.feature.movie_search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shohan.moviediscovery.feature.movie_search.domain.model.SearchMovieResponse
import com.shohan.moviediscovery.feature.movie_search.domain.usecase.SearchMovieUseCase
import com.shohan.moviediscovery.uiUtility.utilities.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchMovieViewModel@Inject constructor(
    private val searchMovieUseCase: SearchMovieUseCase
) : ViewModel() {

    private val _searchMovieState = MutableStateFlow<UiState<SearchMovieResponse>>(UiState.Idle)
    val searchMovieState: StateFlow<UiState<SearchMovieResponse>> = _searchMovieState.asStateFlow()

    val  suggestions = listOf("Avengers", "Spider-Man", "Batman", "Iron Man")


    fun searchMovies(query: String){
        viewModelScope.launch {
            delay(400)
            searchMovieUseCase(query).collect { state ->
                _searchMovieState.value = state
            }
        }
    }

    fun getSuggestionsList(): List<String> {
        return suggestions
    }

}