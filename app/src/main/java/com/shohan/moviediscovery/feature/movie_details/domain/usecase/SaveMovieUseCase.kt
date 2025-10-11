package com.shohan.moviediscovery.feature.movie_details.domain.usecase

import com.shohan.moviediscovery.feature.movie_details.domain.model.MovieDetailsResponse
import com.shohan.moviediscovery.feature.movie_details.domain.repository.MovieDetailsRepo
import javax.inject.Inject

class SaveFavMovieUseCase @Inject constructor(
    private val repo: MovieDetailsRepo
) {
    suspend operator fun invoke(movie: MovieDetailsResponse) = repo.saveFavMovie(movie)
}