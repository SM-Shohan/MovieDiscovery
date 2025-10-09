package com.shohan.moviediscovery.feature.movie_details.domain.usecase

import com.shohan.moviediscovery.feature.movie_details.domain.model.MovieDetailsResponse
import com.shohan.moviediscovery.feature.movie_details.domain.repository.MovieDetailsRepo
import com.shohan.moviediscovery.feature.movie_discovery.domain.model.MovieResponse
import com.shohan.moviediscovery.uiUtility.utilities.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(
    private val movieDetailsRepo: MovieDetailsRepo

) {
    operator fun invoke(movieId: Int): Flow<UiState<MovieDetailsResponse>> = flow {
        emit(UiState.Loading)
        try {
            val result = movieDetailsRepo.getMovieDetails(movieId)
            if (result.success){
                emit(UiState.Success(result))
            }else{
                emit(UiState.Error(result.statusMessage ?: "Something went wrong"))
            }
        }catch (e: Exception){
            emit(UiState.Error(e.message.toString()))
        }

    }.flowOn(Dispatchers.IO)
}