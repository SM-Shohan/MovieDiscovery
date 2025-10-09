package com.shohan.moviediscovery.feature.movie_details.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.shohan.moviediscovery.feature.movie_details.domain.model.MovieDetailsResponse

@Composable
fun MovieDetailsScreen(
    isLoading: Boolean,
    movieResponse: MovieDetailsResponse?
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

    }

}

@Preview (showBackground = true)
@Composable
fun MovieDiscoveryScreenPreview(){
    MovieDetailsScreen(
        isLoading = false,
        movieResponse = null
    )
}