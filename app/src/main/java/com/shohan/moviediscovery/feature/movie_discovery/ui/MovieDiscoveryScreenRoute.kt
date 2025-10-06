package com.shohan.moviediscovery.feature.movie_discovery.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MovieDiscoveryScreenRoute(viewModel: MovieDiscoveryViewModel = hiltViewModel())
{
    val demoString = viewModel.demoString.collectAsState()
    MovieDiscoveryScreen(demoString.value)

}
