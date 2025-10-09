package com.shohan.moviediscovery.feature.movie_discovery.data.mapper

import com.shohan.moviediscovery.feature.movie_discovery.data.dto.MovieDto
import com.shohan.moviediscovery.feature.movie_discovery.domain.model.Movie

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title ?: "",
        originalTitle = originalTitle ?: "",
        overview = overview ?: "",
        posterPath = posterPath,
        backdropPath = backdropPath,
        mediaType = mediaType ?: "",
        originalLanguage = originalLanguage ?: "",
        genreIds = genreIds ?: emptyList(),
        popularity = popularity,
        releaseDate = releaseDate,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}