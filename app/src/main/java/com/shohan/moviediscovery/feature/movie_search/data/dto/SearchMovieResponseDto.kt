package com.shohan.moviediscovery.feature.movie_search.data.dto

import com.google.gson.annotations.SerializedName
import com.shohan.moviediscovery.feature.movie_discovery.data.dto.MovieDto

data class SearchMovieResponseDto(
    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<MovieDto>? = null,
    @SerializedName("status_message")
    val statusMessage: String? = null,

    @SerializedName("success")
    val success: Boolean? = null
)

