package com.shohan.moviediscovery.feature.movie_discovery.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesTopToolbar(
    appName: String = "My Movies",
    onSearchClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        color = Color.White,
        shadowElevation = 4.dp
    ) {
        CenterAlignedTopAppBar(
            title = {
                Column {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = appName,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            },
            navigationIcon = {
                Box(modifier = Modifier.width(0.dp))
            },
            actions = {
                Row(
                    modifier = Modifier.fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onSearchClick,
                        modifier = Modifier.semantics { contentDescription = "Search movies" }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    }

                    IconButton(
                        onClick = onFavoriteClick,
                        modifier = Modifier.semantics { contentDescription = "Add to favorites" }
                    ) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = null
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.White,
                titleContentColor = Color.Black,
                actionIconContentColor = Color.Black
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MoviesTopToolbarPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        MoviesTopToolbar(
            appName = "Movie Explorer",
            onSearchClick = { },
            onFavoriteClick = { }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "(movie list goes here)",
            modifier = Modifier.padding(16.dp)
        )
    }
}
