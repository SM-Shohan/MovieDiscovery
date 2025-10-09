package com.shohan.moviediscovery.feature.movie_discovery.data.mapper

import com.shohan.moviediscovery.feature.movie_discovery.data.dto.PopularMovieResponseDto
import com.shohan.moviediscovery.feature.movie_discovery.domain.model.PopularMovieResponse

fun PopularMovieResponseDto.toDomain(): PopularMovieResponse {
    return PopularMovieResponse(
        page = page,
        movies = results?.map { it.toDomain() } ?: emptyList(),
        statusMessage = statusMessage,
        success = success != false
    )
}