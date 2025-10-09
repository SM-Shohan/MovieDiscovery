package com.shohan.moviediscovery.feature.movie_discovery.data.dto

import com.google.gson.annotations.SerializedName

data class TrendingMovieResponseDto(
    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<MovieDto>? = null,
    @SerializedName("status_message")
    val statusMessage: String? = null,

    @SerializedName("success")
    val success: Boolean? = null
)

