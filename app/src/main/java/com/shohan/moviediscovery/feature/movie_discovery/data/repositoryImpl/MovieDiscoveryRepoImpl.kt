package com.shohan.moviediscovery.feature.movie_discovery.data.repositoryImpl

import com.shohan.moviediscovery.feature.movie_discovery.data.mapper.toDomain
import com.shohan.moviediscovery.feature.movie_discovery.domain.model.PopularMovieResponse
import com.shohan.moviediscovery.feature.movie_discovery.domain.model.TrendingMovieResponse
import com.shohan.moviediscovery.feature.movie_discovery.domain.repository.MovieDiscoveryRepo
import com.shohan.moviediscovery.requestHandler.APIService
import java.io.IOException
import javax.inject.Inject

class MovieDiscoveryRepoImpl @Inject constructor(
    private val apiService: APIService
): MovieDiscoveryRepo {
    override suspend fun getTrendingMovies(): TrendingMovieResponse {
        val apikey = "2528dd64d1c9570839cf27bdca4e7bd8"
        if (apikey.isNotEmpty()){
            val url = "https://api.themoviedb.org/3/trending/movie/day?api_key=${apikey}"
            val response = apiService.getTrendingMovies(url)
            return  if (response.isSuccessful){
                val body = response.body()
                body?.toDomain() ?: throw IOException("Empty response body")
            }else{
                throw IOException("HTTP ${response.code()} ${response.message()}")
            }
        }else{
            return TrendingMovieResponse(page = 0, movies = emptyList(), success = false, statusMessage = "API key is missing")
        }
    }

    override suspend fun getPopularMovies(): PopularMovieResponse {
        val apikey = "2528dd64d1c9570839cf27bdca4e7bd8"
        if (apikey.isNotEmpty()){
            val url = "https://api.themoviedb.org/3/movie/popular?api_key=${apikey}"
            val response = apiService.getPopularMovies(url)
            return  if (response.isSuccessful){
                val body = response.body()
                body?.toDomain() ?: throw IOException("Empty response body")
            }else{
                throw IOException("HTTP ${response.code()} ${response.message()}")
            }
        }else{
            return PopularMovieResponse(page = 0, movies = emptyList(), success = false, statusMessage = "API key is missing")
        }
    }
}