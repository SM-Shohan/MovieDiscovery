package com.shohan.moviediscovery.feature.movie_fav.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shohan.moviediscovery.feature.movie_discovery.domain.model.Movie
import com.shohan.moviediscovery.feature.movie_discovery.ui.components.MovieCard
import com.shohan.moviediscovery.feature.movie_fav.ui.components.FavTopToolbar
import com.shohan.moviediscovery.uiUtility.utilities.AnimatedLoading

@Composable
fun FavMovieScreen(
    isLoading: Boolean = false,
    favMovieList: List<Movie>,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            FavTopToolbar(
                appName = "Favourite Movies",
                onBackClick = {
                    onBackClick()
                },
            )

            if (!isLoading) {
                Text(
                    text = "Favourite Movies",
                    fontSize = 22.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                )

                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(favMovieList) { movie ->
                            MovieCard(
                                movie = movie,
                                onClick = { /* Handle click */ }
                            )
                        }
                    }
                }
            }
        }

        AnimatedLoading(isLoading = isLoading, modifier = Modifier.align(Alignment.Center))
    }
}
