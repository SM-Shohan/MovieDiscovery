package com.shohan.moviediscovery.feature.movie_details.data.repoImpl

import com.shohan.moviediscovery.feature.movie_details.data.mapper.toDomain
import com.shohan.moviediscovery.feature.movie_details.domain.model.MovieDetailsResponse
import com.shohan.moviediscovery.feature.movie_details.domain.repository.MovieDetailsRepo
import com.shohan.moviediscovery.requestHandler.APIService
import java.io.IOException
import javax.inject.Inject

class MovieDetailsRepoImpl @Inject constructor(
    private val apiService: APIService
): MovieDetailsRepo{
    override suspend fun getMovieDetails(movieId: Int): MovieDetailsResponse {
        val apikey = "2528dd64d1c9570839cf27bdca4e7bd8"
        if (apikey.isNotEmpty()){
            val url = "https://api.themoviedb.org/3/movie/${movieId}?api_key=${apikey}"
            val response = apiService.getMovieDetails(url)
            return  if (response.isSuccessful){
                val body = response.body()
                body?.toDomain() ?: throw IOException("Empty response body")
            }else{
                throw IOException("HTTP ${response.code()} ${response.message()}")
            }
        }else{
            return MovieDetailsResponse(
                statusMessage = "API key is missing",
                success = false,
                id = 0,
                title = "",
                overview = "",
                posterUrl = null,
                backdropUrl = null,
                releaseDate = null,
                runtime = null,
                genres = emptyList(),
                productionCompanies = emptyList(),
                productionCountries = emptyList(),
                spokenLanguages = emptyList(),
                popularity = 0.0,
                voteAverage = 0.0,
                voteCount = 0,
                homepage = null,
                status = null,
                tagline = null,
                isAdult = false
            )
        }
    }
}