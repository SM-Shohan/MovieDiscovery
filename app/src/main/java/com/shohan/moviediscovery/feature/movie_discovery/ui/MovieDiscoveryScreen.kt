package com.shohan.moviediscovery.feature.movie_discovery.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MovieDiscoveryScreen(
    demoString: String = ""
)
{
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = demoString)

    }
}

@Preview(showBackground = true)
@Composable
fun MovieDiscoveryScreenPreview() {
    MovieDiscoveryScreen()
}