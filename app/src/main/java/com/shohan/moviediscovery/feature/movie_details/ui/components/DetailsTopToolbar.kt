package com.shohan.moviediscovery.feature.movie_details.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shohan.moviediscovery.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopToolbar(
    appName: String = "Favourites",
    onBackClick: () -> Unit = {},
    isFavourite: Boolean,
    onClickFav: () -> Unit = {},
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
                Image(
                    painter = painterResource(id = R.drawable.outline_arrow_back_24),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .clickable{
                            onBackClick()
                        }
                )
            },
            actions = {
                Column(
                    modifier = Modifier.padding(end = 16.dp).clickable{
                        onClickFav()
                    },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = if (isFavourite){
                            Icons.Default.Favorite
                        }else{
                            Icons.Default.FavoriteBorder
                        },
                        contentDescription = null
                    )
                    Text(
                        text = if (isFavourite){
                            "Remove"
                        }else{
                            "  Add  "
                        },
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                    )
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
        DetailsTopToolbar(
            appName = "Movie Explorer",
            onBackClick = {},
            isFavourite = true,
            onClickFav = {}
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "(movie list goes here)",
            modifier = Modifier.padding(16.dp)
        )
    }
}
