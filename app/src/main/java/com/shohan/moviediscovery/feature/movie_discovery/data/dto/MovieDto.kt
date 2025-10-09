package com.shohan.moviediscovery.feature.movie_discovery.data.dto

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("adult")
    val adult: Boolean = false,

    @SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("original_title")
    val originalTitle: String? = null,

    @SerializedName("overview")
    val overview: String? = null,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("media_type")
    val mediaType: String? = null,

    @SerializedName("original_language")
    val originalLanguage: String? = null,

    @SerializedName("genre_ids")
    val genreIds: List<Int>? = null,

    @SerializedName("popularity")
    val popularity: Double = 0.0,

    @SerializedName("release_date")
    val releaseDate: String? = null,

    @SerializedName("video")
    val video: Boolean = false,

    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,

    @SerializedName("vote_count")
    val voteCount: Int = 0
)