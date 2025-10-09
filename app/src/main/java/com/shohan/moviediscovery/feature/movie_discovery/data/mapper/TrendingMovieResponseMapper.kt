package com.shohan.moviediscovery.feature.movie_discovery.data.mapper

import com.shohan.moviediscovery.feature.movie_discovery.data.dto.TrendingMovieResponseDto
import com.shohan.moviediscovery.feature.movie_discovery.domain.model.TrendingMovieResponse

fun TrendingMovieResponseDto.toDomain(): TrendingMovieResponse {
    return TrendingMovieResponse(
        page = page,
        movies = results?.map { it.toDomain() } ?: emptyList(),
        statusMessage = statusMessage,
        success = success != false
    )
}