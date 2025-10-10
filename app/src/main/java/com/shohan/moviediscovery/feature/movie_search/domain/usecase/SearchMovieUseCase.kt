package com.shohan.moviediscovery.feature.movie_search.domain.usecase

import com.shohan.moviediscovery.feature.movie_search.domain.model.SearchMovieResponse
import com.shohan.moviediscovery.feature.movie_search.domain.repository.SearchMovieRepo
import com.shohan.moviediscovery.uiUtility.utilities.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val searchMovieRepo: SearchMovieRepo
){
    operator fun invoke(query:String): Flow<UiState<SearchMovieResponse>> = flow {
        emit(UiState.Loading)
        try {
            val result = searchMovieRepo.searchMovie(query = query)
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