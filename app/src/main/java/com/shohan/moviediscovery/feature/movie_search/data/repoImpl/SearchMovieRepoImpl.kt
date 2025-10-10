package com.shohan.moviediscovery.feature.movie_search.data.repoImpl

import com.shohan.moviediscovery.feature.movie_search.data.mapper.toDomain
import com.shohan.moviediscovery.feature.movie_search.domain.model.SearchMovieResponse
import com.shohan.moviediscovery.feature.movie_search.domain.repository.SearchMovieRepo
import com.shohan.moviediscovery.requestHandler.APIService
import java.io.IOException
import javax.inject.Inject

class SearchMovieRepoImpl @Inject constructor(
    private val apiService: APIService
) : SearchMovieRepo {
    override suspend fun searchMovie(query: String): SearchMovieResponse {
        val apikey = "2528dd64d1c9570839cf27bdca4e7bd8"
        if (apikey.isNotEmpty()){
            val url = "https://api.themoviedb.org/3/search/movie?api_key=${apikey}&query=${query}"
            val response = apiService.getSearchMovies(url)
            return  if (response.isSuccessful){
                val body = response.body()
                body?.toDomain() ?: throw IOException("Empty response body")
            }else{
                throw IOException("HTTP ${response.code()} ${response.message()}")
            }
        }else{
            return SearchMovieResponse(page = 0, movies = emptyList(), success = false, statusMessage = "API key is missing")
        }
    }

}