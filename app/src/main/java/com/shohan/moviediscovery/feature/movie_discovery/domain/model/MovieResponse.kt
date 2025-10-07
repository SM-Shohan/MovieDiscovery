package com.shohan.moviediscovery.feature.movie_discovery.domain.model

data class MovieResponse(
    val page: Int,
    val movies: List<Movie>,
    val statusMessage: String? = null,
    val success: Boolean
)
