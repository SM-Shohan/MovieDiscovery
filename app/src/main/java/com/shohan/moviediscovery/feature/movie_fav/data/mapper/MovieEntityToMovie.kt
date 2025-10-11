package com.shohan.moviediscovery.feature.movie_fav.data.mapper

import com.shohan.moviediscovery.feature.movie_discovery.domain.model.Movie
import com.shohan.moviediscovery.feature.movie_fav.data.local.MovieEntity

fun MovieEntity.toDomain() = Movie(
    id = id,
    title = title,
    originalTitle = title,
    overview = overview,
    posterPath = posterUrl,
    backdropPath = backdropUrl,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    mediaType = "",
    genreIds = emptyList(),
    originalLanguage = "",
    popularity = 0.0,
    video = false,
    voteCount = voteCount,
)