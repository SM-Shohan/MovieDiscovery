package com.shohan.moviediscovery.feature.movie_fav.domain.usecase

import com.shohan.moviediscovery.feature.movie_fav.domain.repository.FavMovieRepo
import javax.inject.Inject

class GetFavMoviesUseCase @Inject constructor(
    private val repo: FavMovieRepo
) {
    operator fun invoke() = repo.getFavMovies()
}