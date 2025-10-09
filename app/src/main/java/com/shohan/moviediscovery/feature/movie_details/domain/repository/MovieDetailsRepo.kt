package com.shohan.moviediscovery.feature.movie_details.domain.repository

import com.shohan.moviediscovery.feature.movie_details.domain.model.MovieDetailsResponse

interface MovieDetailsRepo {
    suspend fun getMovieDetails(movieId: Int): MovieDetailsResponse
}