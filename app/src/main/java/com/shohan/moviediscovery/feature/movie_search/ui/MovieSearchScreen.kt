package com.shohan.moviediscovery.feature.movie_search.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shohan.moviediscovery.feature.movie_discovery.ui.components.MovieCard
import com.shohan.moviediscovery.feature.movie_search.domain.model.SearchMovieResponse

@Composable
fun MovieSearchScreen(
    searchMovieResponse: SearchMovieResponse?,
    isLoading: Boolean,
    onChangeSearchText: (String) -> Unit,
    onClickMovie: (movieId: Int) -> Unit,
) {
    var query by remember { mutableStateOf("") }
    val searchResults = searchMovieResponse?.movies ?: emptyList()
    val suggestions by remember(searchResults) {
        mutableStateOf(searchResults.take(5).map { it.title })
    }


    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Spacer(modifier = Modifier.height(50.dp))
        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
                onChangeSearchText(it)
            },
            label = { Text("Search movies...") },
            singleLine = true,
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )

        if (suggestions.isNotEmpty() && query.isNotBlank()) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(suggestions) { suggestion ->
                    Text(
                        text = suggestion,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                query = suggestion
                                onChangeSearchText(query)
                            }
                            .padding(vertical = 8.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when {
            isLoading -> Box(Modifier.fillMaxWidth(), Alignment.Center) {
                CircularProgressIndicator()
            }

            searchResults.isNotEmpty() -> {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(searchResults) { movie ->
                        MovieCard (movie = movie) {
                            onClickMovie(it.id)
                        }
                    }
                }
            }

            query.isNotBlank() -> {
                Text(
                    text = "No results for \"$query\"",
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieSearchScreenPreview() {
    MovieSearchScreen(
        searchMovieResponse = null,
        isLoading = false,
        onChangeSearchText = {},
        onClickMovie = {}
    )
}

