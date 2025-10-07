package com.shohan.moviediscovery.requestHandler
import com.shohan.moviediscovery.feature.movie_discovery.data.dto.TrendingMovieResponseDto
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    @GET
    suspend fun getTrendingMovies(
        @Url url: String
    ): Response<TrendingMovieResponseDto>

}
