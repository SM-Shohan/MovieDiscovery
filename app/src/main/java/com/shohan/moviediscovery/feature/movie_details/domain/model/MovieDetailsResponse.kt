package com.shohan.moviediscovery.feature.movie_details.domain.model

data class MovieDetailsResponse(
    val statusMessage: String? = null,
    val success: Boolean,
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String?,
    val backdropUrl: String?,
    val releaseDate: String?,
    val runtime: Int?,
    val genres: List<Genre>,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val spokenLanguages: List<SpokenLanguage>,
    val popularity: Double,
    val voteAverage: Double,
    val voteCount: Int,
    val homepage: String?,
    val status: String?,
    val tagline: String?,
    val isAdult: Boolean
)