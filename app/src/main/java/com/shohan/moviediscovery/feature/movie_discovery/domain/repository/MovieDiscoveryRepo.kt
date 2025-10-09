package com.shohan.moviediscovery.feature.movie_discovery.domain.repository

import com.shohan.moviediscovery.feature.movie_discovery.domain.model.PopularMovieResponse
import com.shohan.moviediscovery.feature.movie_discovery.domain.model.TrendingMovieResponse

interface MovieDiscoveryRepo {
    suspend fun getTrendingMovies(): TrendingMovieResponse
    suspend fun getPopularMovies(): PopularMovieResponse
}