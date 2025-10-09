package com.shohan.moviediscovery.feature.movie_details.data.mapper

import com.shohan.moviediscovery.feature.movie_details.data.dto.GenreDto
import com.shohan.moviediscovery.feature.movie_details.data.dto.MovieDetailsResponseDto
import com.shohan.moviediscovery.feature.movie_details.data.dto.ProductionCompanyDto
import com.shohan.moviediscovery.feature.movie_details.data.dto.ProductionCountryDto
import com.shohan.moviediscovery.feature.movie_details.data.dto.SpokenLanguageDto
import com.shohan.moviediscovery.feature.movie_details.domain.model.Genre
import com.shohan.moviediscovery.feature.movie_details.domain.model.MovieDetailsResponse
import com.shohan.moviediscovery.feature.movie_details.domain.model.ProductionCompany
import com.shohan.moviediscovery.feature.movie_details.domain.model.ProductionCountry
import com.shohan.moviediscovery.feature.movie_details.domain.model.SpokenLanguage


private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
fun MovieDetailsResponseDto.toDomain(): MovieDetailsResponse {
    return MovieDetailsResponse(
        success = success != false,
        statusMessage = statusMessage,
        id = id ?: 0,
        title = title.orEmpty(),
        overview = overview.orEmpty(),
        posterUrl = posterPath?.let { IMAGE_BASE_URL + it },
        backdropUrl = backdropPath?.let { IMAGE_BASE_URL + it },
        releaseDate = releaseDate,
        runtime = runtime,
        genres = genres?.map { it.toDomain() }.orEmpty(),
        productionCompanies = productionCompanies?.map { it.toDomain() }.orEmpty(),
        productionCountries = productionCountries?.map { it.toDomain() }.orEmpty(),
        spokenLanguages = spokenLanguages?.map { it.toDomain() }.orEmpty(),
        popularity = popularity ?: 0.0,
        voteAverage = voteAverage ?: 0.0,
        voteCount = voteCount ?: 0,
        homepage = homepage,
        status = status,
        tagline = tagline,
        isAdult = adult ?: false
    )
}

fun GenreDto.toDomain(): Genre {
    return Genre(
        id = id ?: 0,
        name = name.orEmpty()
    )
}

fun ProductionCompanyDto.toDomain(): ProductionCompany {
    return ProductionCompany(
        id = id ?: 0,
        name = name.orEmpty(),
        logoUrl = logoPath?.let { IMAGE_BASE_URL + it },
        originCountry = originCountry.orEmpty()
    )
}

fun ProductionCountryDto.toDomain(): ProductionCountry {
    return ProductionCountry(
        code = iso31661.orEmpty(),
        name = name.orEmpty()
    )
}

fun SpokenLanguageDto.toDomain(): SpokenLanguage {
    return SpokenLanguage(
        code = iso6391.orEmpty(),
        name = englishName.orEmpty()
    )
}