package com.shohan.moviediscovery.feature.movie_search.data.mapper

import com.shohan.moviediscovery.feature.movie_discovery.data.mapper.toDomain
import com.shohan.moviediscovery.feature.movie_search.data.dto.SearchMovieResponseDto
import com.shohan.moviediscovery.feature.movie_search.domain.model.SearchMovieResponse

fun SearchMovieResponseDto.toDomain(): SearchMovieResponse {
    return SearchMovieResponse(
        page = page,
        movies = results?.map { it.toDomain() } ?: emptyList(),
        statusMessage = statusMessage,
        success = success != false
    )
}