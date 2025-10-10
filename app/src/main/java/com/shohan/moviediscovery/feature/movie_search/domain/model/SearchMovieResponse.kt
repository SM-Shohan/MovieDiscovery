package com.shohan.moviediscovery.feature.movie_search.domain.model

import com.shohan.moviediscovery.feature.movie_discovery.domain.model.Movie

data class SearchMovieResponse(
    val page: Int,
    val movies: List<Movie>,
    val statusMessage: String? = null,
    val success: Boolean
)
