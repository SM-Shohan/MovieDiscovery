package com.shohan.moviediscovery.feature.movie_fav.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shohan.moviediscovery.feature.movie_discovery.domain.model.Movie
import com.shohan.moviediscovery.feature.movie_fav.domain.usecase.GetFavMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavMovieViewModel@Inject constructor(
    private val getFavMoviesUseCase: GetFavMoviesUseCase
): ViewModel()
 {

     private val _favMovies = MutableStateFlow<List<Movie>>(emptyList())
     val favMovies = _favMovies.asStateFlow()

     init {
         viewModelScope.launch {
             getFavMoviesUseCase().collect {
                 _favMovies.value = it
             }
         }
     }
}