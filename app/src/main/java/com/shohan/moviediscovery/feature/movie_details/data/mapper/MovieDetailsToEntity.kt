package com.shohan.moviediscovery.feature.movie_details.data.mapper

import com.shohan.moviediscovery.feature.movie_details.domain.model.MovieDetailsResponse
import com.shohan.moviediscovery.feature.movie_fav.data.local.MovieEntity

fun MovieDetailsResponse.toEntity() = MovieEntity(
    id = id,
    title = title,
    overview = overview,
    posterUrl = posterUrl,
    backdropUrl = backdropUrl,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    voteCount = voteCount
)