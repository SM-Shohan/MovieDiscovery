package com.shohan.moviediscovery.feature.movie_details.domain.model

data class ProductionCompany(
    val id: Int,
    val name: String,
    val logoUrl: String?,
    val originCountry: String
)