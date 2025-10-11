package com.shohan.moviediscovery.feature.movie_fav.data.repositoryImpl

import com.shohan.moviediscovery.feature.movie_discovery.domain.model.Movie
import com.shohan.moviediscovery.feature.movie_fav.data.local.FavMovieDao
import com.shohan.moviediscovery.feature.movie_fav.data.mapper.toDomain
import com.shohan.moviediscovery.feature.movie_fav.domain.repository.FavMovieRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavMovieRepoImpl @Inject constructor(
    private val favMovieDao: FavMovieDao
): FavMovieRepo{
    override fun getFavMovies(): Flow<List<Movie>> {
        return favMovieDao.getAllMovies().map { list ->
            list.map { it.toDomain() }
        }
    }

}