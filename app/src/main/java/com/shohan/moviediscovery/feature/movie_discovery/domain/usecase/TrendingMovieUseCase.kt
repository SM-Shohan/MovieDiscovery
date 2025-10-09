package com.shohan.moviediscovery.feature.movie_discovery.domain.usecase

import com.shohan.moviediscovery.feature.movie_discovery.domain.model.TrendingMovieResponse
import com.shohan.moviediscovery.feature.movie_discovery.domain.repository.MovieDiscoveryRepo
import com.shohan.moviediscovery.uiUtility.utilities.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TrendingMovieUseCase @Inject constructor(
    private val movieDiscoveryRepo: MovieDiscoveryRepo
){
    operator fun invoke(): Flow<UiState<TrendingMovieResponse>> = flow {
        emit(UiState.Loading)
        try {
            val result = movieDiscoveryRepo.getTrendingMovies()
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