package com.shohan.moviediscovery.feature.movie_discovery.domain.repository

import com.shohan.moviediscovery.feature.movie_discovery.domain.model.MovieResponse

interface MovieDiscoveryRepo {
    suspend fun getTrendingMovies(): MovieResponse
}