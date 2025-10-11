package com.shohan.moviediscovery.feature.movie_fav.domain.repository

import com.shohan.moviediscovery.feature.movie_discovery.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface FavMovieRepo {
    fun getFavMovies(): Flow<List<Movie>>
}