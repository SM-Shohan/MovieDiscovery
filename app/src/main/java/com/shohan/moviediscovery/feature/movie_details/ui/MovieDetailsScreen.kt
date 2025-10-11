package com.shohan.moviediscovery.feature.movie_details.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shohan.moviediscovery.feature.movie_details.domain.model.MovieDetailsResponse
import com.shohan.moviediscovery.feature.movie_details.ui.components.DetailsTopToolbar
import com.shohan.moviediscovery.uiUtility.utilities.AnimatedLoading

@Composable
fun MovieDetailsScreen(
    isLoading: Boolean,
    isFavMovie: Boolean,
    onClickFav: (movie: MovieDetailsResponse) -> Unit,
    movieResponse: MovieDetailsResponse?,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        val scrollState = rememberScrollState()
        Column {
            DetailsTopToolbar(
                appName = "Movie Details",
                onBackClick = {
                    onBackClick()
                },
                onClickFav = {
                    movieResponse?.let { movie->
                        onClickFav(movie)
                    }
                },
                isFavourite = isFavMovie,
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .verticalScroll(scrollState)
            ) {
                movieResponse?.backdropUrl?.let { backdrop ->
                    AsyncImage(
                        model = backdrop,
                        contentDescription = movieResponse.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .offset(y = (-60).dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    movieResponse?.posterUrl?.let { poster ->
                        AsyncImage(
                            model = poster,
                            contentDescription = movieResponse.title,
                            modifier = Modifier
                                .width(140.dp)
                                .height(210.dp)
                                .clip(MaterialTheme.shapes.medium),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = movieResponse?.title ?: "",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = MaterialTheme.colorScheme.onBackground,
                            maxLines = 2
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = movieResponse?.genres?.joinToString(", ") { it.name } ?: "",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "⭐ ${movieResponse?.voteAverage}  |  ${movieResponse?.releaseDate ?: "N/A"}  |  ${movieResponse?.runtime ?: 0} min",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text(
                        text = "Overview",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = movieResponse?.overview?.ifBlank { "No overview available." } ?: "",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            lineHeight = 20.sp
                        ),
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
                        textAlign = TextAlign.Justify
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                if (movieResponse?.productionCompanies?.isNotEmpty() == true) {
                    Text(
                        text = "Produced by: " + movieResponse.productionCompanies.joinToString(", ") { it.name },
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        AnimatedLoading(isLoading = isLoading, modifier = Modifier.align(Alignment.Center))

    }

}

@Preview (showBackground = true)
@Composable
fun MovieDiscoveryScreenPreview(){
    MovieDetailsScreen(
        isLoading = false,
        isFavMovie = false,
        onClickFav = {},
        movieResponse = null,
        onBackClick = {}
    )
}