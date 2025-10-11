package com.shohan.moviediscovery.feature.movie_details.domain.usecase

import com.shohan.moviediscovery.feature.movie_details.domain.repository.MovieDetailsRepo
import javax.inject.Inject

class IsFavMovieUseCase @Inject constructor(
    private val repo: MovieDetailsRepo
) {
    suspend operator fun invoke(movieId: Int) = repo.isMovieFav(movieId)
}