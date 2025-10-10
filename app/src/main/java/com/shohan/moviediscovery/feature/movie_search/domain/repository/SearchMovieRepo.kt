package com.shohan.moviediscovery.feature.movie_search.domain.repository

import com.shohan.moviediscovery.feature.movie_search.domain.model.SearchMovieResponse

interface SearchMovieRepo {
    suspend fun searchMovie(query: String): SearchMovieResponse
}