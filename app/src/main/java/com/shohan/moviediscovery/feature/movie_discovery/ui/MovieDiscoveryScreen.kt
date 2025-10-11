package com.shohan.moviediscovery.feature.movie_discovery.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.shohan.moviediscovery.feature.movie_discovery.domain.model.Movie
import com.shohan.moviediscovery.feature.movie_discovery.domain.model.PopularMovieResponse
import com.shohan.moviediscovery.feature.movie_discovery.domain.model.TrendingMovieResponse
import com.shohan.moviediscovery.feature.movie_discovery.ui.components.MovieCard
import com.shohan.moviediscovery.feature.movie_discovery.ui.components.MoviesTopToolbar
import com.shohan.moviediscovery.feature.movie_discovery.ui.components.genreMap
import com.shohan.moviediscovery.uiUtility.utilities.AnimatedLoading

@Composable
fun MovieDiscoveryScreen(
    trendingMovieResponse: TrendingMovieResponse?,
    popularMovieResponse: PopularMovieResponse?,
    isLoading: Boolean = false,
    onClickMovie: (movieId: Int) -> Unit,
    onToolbarSearchClick: () -> Unit,
    onToolbarFavoriteClick: () -> Unit
)
{
    val moviesList = popularMovieResponse?.movies ?: emptyList()
    val genreToMoviesMap = mutableMapOf<Int, MutableList<Movie>>()

    for (movie in moviesList) {
        for (genreId in movie.genreIds) {
            val movieList = genreToMoviesMap.getOrPut(genreId) { mutableListOf() }
            movieList.add(movie)
        }
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            MoviesTopToolbar(
                appName = "Movie Discovery",
                onSearchClick = {
                    onToolbarSearchClick()
                },
                onFavoriteClick = {
                    onToolbarFavoriteClick()

                }
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(40.dp))
                }
                if (!isLoading) {
                    item {
                        Text(
                            text = "Trending Movies",
                            fontSize = 22.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                        )
                    }
                }

                item {
                    LazyRow(
                        contentPadding = PaddingValues(start = 16.dp, end = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(trendingMovieResponse?.movies ?: emptyList()) { movie ->
                            MovieCard(movie = movie, onClick = {
                                onClickMovie(
                                    movie.id
                                )
                            })
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(30.dp))
                }
                if (!isLoading) {
                    item {
                        Text(
                            text = "Popular Movies",
                            fontSize = 22.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                        )
                    }
                }

                genreToMoviesMap.forEach { (genreId, movies) ->
                    val genreName = genreMap[genreId] ?: "Unknown"

                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        ) {
                            // Genre Title
                            Text(
                                text = genreName,
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 16.dp, bottom = 4.dp)
                            )

                            // Horizontal movie list
                            LazyRow(
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(movies) { movie ->
                                    MovieCard(
                                        movie = movie,
                                        onClick = { onClickMovie(movie.id) }
                                    )
                                }
                            }
                        }
                    }
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
        trendingMovieResponse = null,
        popularMovieResponse = null,
        onClickMovie = {},
        onToolbarSearchClick = {},
        onToolbarFavoriteClick = {}
    )
}