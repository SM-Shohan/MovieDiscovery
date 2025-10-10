package com.shohan.moviediscovery.requestHandler
import com.shohan.moviediscovery.feature.movie_details.data.dto.MovieDetailsResponseDto
import com.shohan.moviediscovery.feature.movie_discovery.data.dto.PopularMovieResponseDto
import com.shohan.moviediscovery.feature.movie_discovery.data.dto.TrendingMovieResponseDto
import com.shohan.moviediscovery.feature.movie_search.data.dto.SearchMovieResponseDto
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    @GET
    suspend fun getTrendingMovies(
        @Url url: String
    ): Response<TrendingMovieResponseDto>

    @GET
    suspend fun getSearchMovies(
        @Url url: String
    ): Response<SearchMovieResponseDto>
    @GET
    suspend fun getPopularMovies(
        @Url url: String
    ): Response<PopularMovieResponseDto>

    @GET
    suspend fun getMovieDetails(
        @Url url: String
    ): Response<MovieDetailsResponseDto>

}
