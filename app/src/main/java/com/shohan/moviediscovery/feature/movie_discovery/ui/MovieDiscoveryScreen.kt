package com.shohan.moviediscovery.feature.movie_discovery.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shohan.moviediscovery.feature.movie_discovery.domain.model.MovieResponse
import com.shohan.moviediscovery.feature.movie_discovery.ui.components.MovieCard
import com.shohan.moviediscovery.uiUtility.utilities.AnimatedLoading

@Composable
fun MovieDiscoveryScreen(
    movieResponse: MovieResponse?,
    isLoading: Boolean = false
)
{
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "title",
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )

            LazyRow(
                contentPadding = PaddingValues(start = 16.dp, end = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(movieResponse?.movies ?: emptyList()) { movie ->
                    MovieCard(movie = movie, onClick = {  })
                }
            }
        }
        AnimatedLoading(isLoading = isLoading, modifier = Modifier.align(Alignment.Center))
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDiscoveryScreenPreview() {
    MovieDiscoveryScreen(
        isLoading = false,
        movieResponse = null
    )
}